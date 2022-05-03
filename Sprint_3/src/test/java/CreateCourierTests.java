import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateCourierTest {
    private ScooterTestSteps testSteps = new ScooterTestSteps();
    private List<CourierLogin> testCourierLoginList = new ArrayList<CourierLogin>();
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
    }

    @After
    public void cleanUp() {
        for (CourierLogin testCourierLogin : testCourierLoginList)
            testSteps.deleteCourier(testCourierLogin);
    }

    @Test
    @DisplayName("Создание нового курьера по логину и паролю")
    @Description("Передаём логин и пароль - ожидаем код 201 и ответ {ok: true}")
    public void shouldCreateCourierByLoginPassword() {
        CourierLogin newCourier = new CourierLogin(testSteps.genRandomAlfaString(), testSteps.genRandomAlfaNumString());
        Response createNewCourierResponse = testSteps.createCourier(newCourier);
        testSteps.checkCreateCourierResponse(createNewCourierResponse);
    }

    @Test
    @DisplayName("Создание нового курьера по логину, паролю и имени")
    @Description("Передаём логин, пароль и необязательный параметр name - ожидаем код 201 и ответ {ok: true}")
    public void shouldCreateCourierByLoginPasswordName() {
        CourierLogin newCourier = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(),
                testSteps.genRandomAlfaString());
        Response createNewCourierResponse = testSteps.createCourier(newCourier);
        testSteps.checkCreateCourierResponse(createNewCourierResponse);
    }

    @Test
    @DisplayName("Ошибка при создании уже имеющегося курьера")
    @Description("Передаём учетные данные уже созданного заранее курьера -\n" +
            "ожидаем код ошибки 409 и сообщение {message: Этот логин уже используется}")
    public void errorCreateExistingCourier() {
        Response createNewCourierResponse = testSteps.createCourier(testCourierLoginList.get(0));
        testSteps.checkCreateExistingCourierResponse(createNewCourierResponse);
    }

    @Test
    @DisplayName("Ошибка при создании курьера с уже имеющимся логином")
    @Description("Передаём новые учётные данные с уже сохранённым заранее логином курьера -\n" +
            "ожидаем код ошибки 409 и сообщение {message: Этот логин уже используется. Попробуйте другой.}")
    public void errorCreateCourierWithExistingLogin() {
        CourierLogin newCourier = new CourierLogin(testCourierLoginList.get(0).getLogin(),
                testSteps.genRandomAlfaNumString(),
                testSteps.genRandomAlfaString());
        Response createNewCourierResponse = testSteps.createCourier(testCourierLoginList.get(0));
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
        Response createNewCourierResponse = testSteps.createCourier(testCourierLoginList.get(0));
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
        Response createNewCourierResponse = testSteps.createCourier(testCourierLoginList.get(0));
        testSteps.checkCreateCourierWithoutRequiredParams(createNewCourierResponse);
    }
}
