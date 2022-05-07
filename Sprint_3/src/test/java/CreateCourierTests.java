import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.*;
import org.junit.After;
import org.junit.Test;
import io.restassured.response.Response;


public class CreateCourierTests {
    private ScooterTestSteps testSteps = new ScooterTestSteps();
    private CourierLogin testCourier;
    private boolean testCourierCreated;

    @After
    public void cleanUpCourier() {
        if (testCourierCreated) testSteps.deleteCourier(testCourier);
    }

    @Test
    @DisplayName("Создание нового курьера по логину и паролю")
    @Description("Передаём логин и пароль - ожидаем код 201 и ответ {ok: true}")
    public void shouldCreateCourierByLoginPassword() {
        testCourier = new CourierLogin(testSteps.genRandomAlfaString(), testSteps.genRandomAlfaNumString());
        Response createNewCourierResponse = testSteps.createCourier(testCourier);
        Long courierId = testSteps.loginCourier(testCourier).then().extract().as(CourierLogin.class).getId();
        testCourier.setId(courierId);
        testCourierCreated = true;
        testSteps.checkCreateCourierResponse(createNewCourierResponse);
    }

    @Test
    @DisplayName("Создание нового курьера по логину, паролю и имени")
    @Description("Передаём логин, пароль и необязательный параметр name - ожидаем код 201 и ответ {ok: true}")
    public void shouldCreateCourierByLoginPasswordName() {
        testCourier = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(),
                testSteps.genRandomAlfaString());
        Response createNewCourierResponse = testSteps.createCourier(testCourier);
        Long courierId = testSteps.loginCourier(testCourier).then().extract().as(CourierLogin.class).getId();
        testCourier.setId(courierId);
        testCourierCreated = true;
        testSteps.checkCreateCourierResponse(createNewCourierResponse);
    }

    @Test
    @DisplayName("Ошибка при создании уже имеющегося курьера")
    @Description("Передаём учетные данные уже созданного заранее курьера -\n" +
            "ожидаем код ошибки 409 и сообщение {message: Этот логин уже используется}")
    public void errorCreateExistingCourier() {
        testCourier = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(), testSteps.genRandomAlfaString());
        testSteps.createCourier(testCourier);
        Response createNewCourierResponse = testSteps.createCourier(testCourier);
        Long courierId = testSteps.loginCourier(testCourier).then().extract().as(CourierLogin.class).getId();
        testCourier.setId(courierId);
        testCourierCreated = true;
        testSteps.checkCreateExistingCourierResponse(createNewCourierResponse);
    }

    @Test
    @DisplayName("Ошибка при создании курьера с уже имеющимся логином")
    @Description("Передаём новые учётные данные с уже сохранённым заранее логином курьера -\n" +
            "ожидаем код ошибки 409 и сообщение {message: Этот логин уже используется. Попробуйте другой.}")
    public void errorCreateCourierWithExistingLogin() {
        testCourier = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(), testSteps.genRandomAlfaString());
        testSteps.createCourier(testCourier);
        Long courierId = testSteps.loginCourier(testCourier).then().extract().as(CourierLogin.class).getId();
        testCourier.setId(courierId);
        testCourierCreated = true;
        CourierLogin newCourier = new CourierLogin(testCourier.getLogin(),
                testSteps.genRandomAlfaNumString(),
                testSteps.genRandomAlfaString());
        Response createNewCourierResponse = testSteps.createCourier(newCourier);
        testSteps.checkCreateExistingCourierResponse(createNewCourierResponse);
    }

    @Test
    @DisplayName("Ошибка при создании курьера без логина")
    @Description("Передаём новые учётные данные без логина -\n" +
            "ожидаем код ошибки 400 и сообщение {message: Недостаточно данных для создания учетной записи}")
    public void errorCreateCourierWithoutLogin() {
        CourierLogin newCourier = new CourierLogin();
        newCourier.setPassword(testSteps.genRandomAlfaNumString());
        newCourier.setFirstName(testSteps.genRandomAlfaString());
        Response createNewCourierResponse = testSteps.createCourier(newCourier);
        testCourierCreated = false;
        testSteps.checkCreateCourierWithoutRequiredParams(createNewCourierResponse);
    }

    @Test
    @DisplayName("Ошибка при создании курьера без пароля")
    @Description("Передаём новые учётные данные без пароля -\n" +
            "ожидаем код ошибки 400 и сообщение {message: Недостаточно данных для создания учетной записи}")
    public void errorCreateCourierWithoutPassword() {
        CourierLogin newCourier = new CourierLogin();
        newCourier.setLogin(testSteps.genRandomAlfaString());
        newCourier.setFirstName(testSteps.genRandomAlfaString());
        Response createNewCourierResponse = testSteps.createCourier(newCourier);
        testCourierCreated = false;
        testSteps.checkCreateCourierWithoutRequiredParams(createNewCourierResponse);
    }
}
