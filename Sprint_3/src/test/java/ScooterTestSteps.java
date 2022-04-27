import client.QaScooterClient;
import models.CourierLogin;
import org.apache.commons.lang3.RandomStringUtils;
import static org.assertj.core.api.Assertions.assertThat;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.error.ShouldBeTrue;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;


public class ScooterTestSteps {

    QaScooterClient client = new QaScooterClient();

    public String genRandomAlfaString() {
        return RandomStringUtils.randomAlphabetic(6, 16);
    }

    public String genRandomAlfaNumString() {
        return RandomStringUtils.randomAlphanumeric(4, 16);
    }

    public HashMap<String, String> createCourier(String login, String password, String name) {
        HashMap<String, String> courierMap = new HashMap<String, String>();
        Response response = client.createCourier(new CourierLogin(login, password, name));
        courierMap.put(login, password);
        response.then().assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", is(true));
        return courierMap;
    }

    public HashMap<String, String> createCourier(String login, String password) {
        HashMap<String, String> courierMap = new HashMap<String, String>();
        Response response = client.createCourier(new CourierLogin(login, password));
        courierMap.put(login, password);
        response.then().assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", is(true));
        return courierMap;
    }

    public Long loginCourier(CourierLogin login) {
        CourierLogin response = client.postLogin(login);
        return response.getId();
    }
}
