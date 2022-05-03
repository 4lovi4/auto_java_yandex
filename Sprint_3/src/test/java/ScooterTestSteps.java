import client.QaScooterClient;
import models.CourierLogin;
import models.OkResponse;
import models.Order;
import models.OrderList;
import org.apache.commons.lang3.RandomStringUtils;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;


public class ScooterTestSteps {

    QaScooterClient client = new QaScooterClient();

    public String genRandomAlfaString() {
        return RandomStringUtils.randomAlphabetic(6, 16);
    }

    public String genRandomAlfaNumString() {
        return RandomStringUtils.randomAlphanumeric(4, 16);
    }

    public String genRandomPhoneNumber() {return "+7" + RandomStringUtils.randomNumeric(10);};

    public Long randomNum() { return RandomUtils.nextLong(1L, 10L); }

    @Step("Создание курьера POST /api/v1/courier")
    public Response createCourier(CourierLogin courierLogin) {
        return client.createCourier(courierLogin);
    }

    @Step("Проверка успешного ответа на создание курьера")
    public void checkCreateCourierResponse(Response createCourierResponse) {
        assertThat(createCourierResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_CREATED);
        assertThat(createCourierResponse.then().extract().as(OkResponse.class).isOk()).isTrue();
    }

    @Step("Проверка ошибочного ответа на создание существующего курьера")
    public void checkCreateExistingCourierResponse(Response createCourierResponse) {
        assertThat(createCourierResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
        assertThat(createCourierResponse.then().extract().path("message").toString())
                .isEqualTo("Этот логин уже используется. Попробуйте другой.");
    }

    @Step("Проверка успешного ответа на создание клиента")
    public void checkCreateCourierWithoutRequiredParams(Response createCourierResponse) {
        assertThat(createCourierResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        assertThat(createCourierResponse.then().extract().path("message").toString())
                .isEqualTo("Недостаточно данных для создания учетной записи");
    }

    @Step("Вход курьера c логином POST /api/v1/courier/login")
    protected Response loginCourier(CourierLogin login) {
        return client.postLogin(login);
    }

    @Step("Проверка успешной авторизации курьера - \n" +
    "возвращается код 200 и id залогиненного курьера")
    protected Long checkSuccessCourierLoginAndReturnId(Response loginResponse) {
        assertThat(loginResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_OK);
        Long courierId = loginResponse.then().extract().as(CourierLogin.class).getId();
        assertThat(courierId).isNotNull().isPositive();
        return courierId;
    }

    @Step("Проверка ошибки авторизации курьера без всех обязательных полей")
    protected void checkLoginWithoutRequiredParams(Response loginResponse) {
        assertThat(loginResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        assertThat(loginResponse.then().extract().path("message").toString())
                .isEqualTo("Недостаточно данных для входа");
    }

    @Step("Проверка ошибки авторизации курьера с неверными учётными данными")
    protected void checkWrongLogin(Response loginResponse) {
        assertThat(loginResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(loginResponse.then().extract().path("message").toString())
                .isEqualTo("Учетная запись не найдена");
    }

    @Step("Удаление курьера DELETE /api/v1/courier/:id")
    protected Response deleteCourier(CourierLogin login) {
        return client.deleteCourier(login.getId());
    }

    @Step("Успешное Создание заказа")
    protected Response createOrder(Order order) {
        return client.createOrder(order);
    }

    @Step("Проверка ответа при создании ордера")
    protected Long checkOrderCreatedAndReturnTrackId(Response orderResponse) {
        assertThat(orderResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_CREATED);
        Long trackId = Long.valueOf(orderResponse.then().extract().path("track").toString());
        assertThat(trackId).isNotNull().isPositive();
        return trackId;
    }

    @Step("Вызов метода POST /api/v1/orders отмена заказа {trackId}")
    protected Response cancelOrder(Long trackId) {
        return client.cancelOrder(trackId);
    }

    @Step("Получение списка заказов GET /api/v1/orders")
    protected Response listOrders() {
        return client.listOrders();
    }

    @Step("Получение списка заказов по id курьера GET /api/v1/orders?courierId=")
    protected  Response listOrdersByCourierId(Long courierId) {
        return client.listOrders(courierId);
    }

    @Step("Проверка ответа списка заказов")
    protected void checkOrderListResponse(Response orderListResponse) {
        assertThat(orderListResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat((List<?>) orderListResponse.then().extract().path("orders")).isNotNull().isInstanceOf(ArrayList.class);
    }

    @Step("Проверка ответа списка заказов по Id курьера")
    protected void checkOrderListResponse(Response orderListResponse, Long courierId) {
        assertThat(orderListResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_OK);
        OrderList orderList = orderListResponse.then().extract().as(OrderList.class);
        assertThat(orderList.getOrders()).isNotNull().isInstanceOf(ArrayList.class);
    }

    @Step("Ошибка получения списка заказов по несуществующему Id курьера")
    protected void checkErrorOrderListResponse(Response orderListResponse, Long courierId) {
        assertThat(orderListResponse.then().extract().statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(orderListResponse.then().extract().path("message").toString())
                .isEqualTo(String.format("Курьер с идентификатором %d не найден", courierId));
    }
}
