import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.*;
import org.assertj.core.util.Lists;
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


@RunWith(Parameterized.class)
public class CreateOrderTests {
    private ScooterTestSteps testSteps = new ScooterTestSteps();
    private Long orderTrackId;

    @Parameter
    public ArrayList<String> scooterColor;

    @Parameters
    public static Object[] colors() {
        return new Object[]{
                Lists.newArrayList("BLACK"),
                Lists.newArrayList("GREY"),
                Lists.newArrayList("BLACK", "GREY"),
                Lists.newArrayList()
        };
    }


    @After
    public void cleanUp() {
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
