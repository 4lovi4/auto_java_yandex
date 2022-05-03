import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class LoginCourierTests {

    private ScooterTestSteps testSteps = new ScooterTestSteps();
    private List<CourierLogin> testCourierLoginList = new ArrayList<CourierLogin>();
    private Long testCourierId;
    private CourierLogin testCourierLogin;


    @Before
    public void prepareTest() {
        testCourierLogin = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(), testSteps.genRandomAlfaString());
        testSteps.createCourier(testCourierLogin);
    }

    @After
    public void cleanUp() {
        for (CourierLogin testCourierLogin : testCourierLoginList)
            testSteps.deleteCourier(testCourierLogin);
    }

    @Test
    @DisplayName("Успешная авторизация курьера")
    @Description("Передаются все обязательные поля для логина курьера \n" +
            "Возвращается код 200 и id залогиненного курьера")
    public void shouldSuccessLogin() {
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        testCourierId = testSteps.checkSuccessCourierLoginAndReturnId(loginResponse);
        testCourierLogin.setId(testCourierId);
        testCourierLoginList.add(testCourierLogin);
    }

    @Test
    @DisplayName("Ошибка авторизации при неправильном логине курьера")
    @Description("Передаются все обязательные поля для логина курьера, но логин не совпадает \n" +
            "Возвращается код 404 и сообщение {\"message\":  \"Учетная запись не найдена\"}")
    public void errorWrongLogin() {
        testCourierLogin.setLogin(testSteps.genRandomAlfaString());
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        testSteps.checkWrongLogin(loginResponse);
    }

    @Test
    @DisplayName("Ошибка авторизации при неправильном пароле курьера")
    @Description("Передаются все обязательные поля для логина курьера, но пароль не совпадает \n" +
            "Возвращается код 404 и сообщение {\"message\":  \"Учетная запись не найдена\"}")
    public void errorWrongPassword() {
        testCourierLogin.setPassword(testSteps.genRandomAlfaNumString());
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        testSteps.checkWrongLogin(loginResponse);
    }

    @Test
    @DisplayName("Ошибка авторизации при указании учетных данных несуществующего курьера")
    @Description("Передаются все обязательные поля для логина курьера, но такого курьера не существует \n" +
            "Возвращается код 404 и сообщение {\"message\":  \"Учетная запись не найдена\"}")
    public void errorNonExistingCourier() {
        CourierLogin testCourierLogin = new CourierLogin(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(), testSteps.genRandomAlfaString());
        testCourierLogin.setLogin(testSteps.genRandomAlfaString());
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        testSteps.checkWrongLogin(loginResponse);
    }

    @Test
    @DisplayName("Ошибка авторизации без указания логина")
    @Description("Не передаётся логин курьера \n" +
            "Возвращается код 400 и сообщение {\"message\":  \"Недостаточно данных для входа\"}")
    public void errorWithoutLogin() {
        testCourierLogin.setLogin(null);
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        testSteps.checkLoginWithoutRequiredParams(loginResponse);
    }

    /*
    Ошибка возвращается ошибка 504 Service unavailable если не указать пароль
     */
    @Test
    @DisplayName("Ошибка авторизации без указания пароля")
    @Description("Не передаётся пароль курьера\n" +
            "Возвращается код 400 и сообщение {\"message\":  \"Недостаточно данных для входа\"}")
    public void errorWithoutPassword() {
        testCourierLogin.setPassword(null);
        Response loginResponse = testSteps.loginCourier(testCourierLogin);
        testSteps.checkLoginWithoutRequiredParams(loginResponse);
    }
}
