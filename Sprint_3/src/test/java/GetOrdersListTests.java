import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.*;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import io.restassured.response.Response;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetOrdersListTests {
    private ScooterTestSteps testSteps = new ScooterTestSteps();
    private List<CourierLogin> testCourierLoginList = new ArrayList<CourierLogin>();
    private Long orderTrackId;
    private Long testCourierId;

    @Before
    public void prepareTest() {
        CourierLogin testCourierLogin = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(), testSteps.genRandomAlfaString());
        testSteps.createCourier(testCourierLogin);
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        testCourierId = Long.valueOf(loginResponse.then().extract().path("id").toString());
        testCourierLogin.setId(testCourierId);
        testCourierLoginList.add(testCourierLogin);
        Order newTestOrder = new Order(
                testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaString(),
                "Станция Тестовая",
                testSteps.genRandomPhoneNumber(),
                testSteps.randomNum(),
                DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()),
                testSteps.genRandomAlfaString(),
                Lists.newArrayList("BLACK", "GREY"));
        Response orderResponse = testSteps.createOrder(newTestOrder);
        orderTrackId = Long.valueOf(orderResponse.then().extract().path("track").toString());
    }

    @After
    public void cleanUp() {
        for (CourierLogin testCourierLogin : testCourierLoginList)
            testSteps.deleteCourier(testCourierLogin);
        testSteps.cancelOrder(orderTrackId);
    }

    @Test
    @DisplayName("Запрос списка заказов без параметров")
    @Description("Ответ с кодом 200 и списком заказов")
    public void shouldGetOrderList() {
        Response orderListResponse = testSteps.listOrders();
        testSteps.checkOrderListResponse(orderListResponse);
    }

    @Test
    @DisplayName("Запрос списка заказов, с указанием id курьера в параметрах")
    @Description("Ответ с кодом 200 и списком заказов по курьеру")
    public void shouldGetOrderListByCourierId() {
        Response orderListResponse = testSteps.listOrdersByCourierId(testCourierId);
        testSteps.checkOrderListResponse(orderListResponse, testCourierId);
    }

    @Test
    @DisplayName("Запрос списка заказов с несуществующим id курьера")
    @Description("В ответ на запрос с несуществующим значением courierId код ошибки 404\n" +
    "сообщение {\"message\": \"Курьер с идентификатором {courierId} не найден\"}")
    public void errorGetOrderListWithWrongCourierId() {
        Response orderListResponse = testSteps.listOrdersByCourierId(testCourierId * 1000L);
        testSteps.checkErrorOrderListResponse(orderListResponse, testCourierId * 1000L);
    }

}
