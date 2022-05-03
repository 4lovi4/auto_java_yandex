import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.*;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTests {
    private ScooterTestSteps testSteps = new ScooterTestSteps();
    private List<CourierLogin> testCourierLoginList = new ArrayList<CourierLogin>();
    private Long orderTrackId;

    @Parameter
    public ArrayList<String> scooterColor;

    @Parameters
    public static Object[] colors() {
        return new Object[]{
                Lists.newArrayList("BLACK"),
                Lists.newArrayList("GREY"),
                Lists.newArrayList("BLACK", "GREY")
        };
    }

    @Before
    public void prepareTest() {
        CourierLogin testCourierLogin = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(), testSteps.genRandomAlfaString());
        testSteps.createCourier(testCourierLogin);
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        Long testCourierId = Long.valueOf(loginResponse.then().extract().path("id").toString());
        testCourierLogin.setId(testCourierId);
        testCourierLoginList.add(testCourierLogin);
    }

    @After
    public void cleanUp() {
        for (CourierLogin testCourierLogin : testCourierLoginList)
            testSteps.deleteCourier(testCourierLogin);
        testSteps.cancelOrder(orderTrackId);
    }

    @Test
    @DisplayName("Создание заказа в зависимости от цвета")
    @Description("Запрос на создание нового заказа - \n" +
    "ожидаемый код ответа 201 и track номер заказа")
    public void createNewOrderByColor() {
        Order newTestOrder = new Order(
            testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaString(),
                "Станция Тестовая",
                testSteps.genRandomPhoneNumber(),
                testSteps.randomNum(),
                DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()),
                testSteps.genRandomAlfaString(),
                scooterColor);
        Response orderResponse = testSteps.createOrder(newTestOrder);
        orderTrackId = testSteps.checkOrderCreatedAndReturnTrackId(orderResponse);
    }
}
