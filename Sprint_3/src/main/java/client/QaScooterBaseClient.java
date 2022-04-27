package client;

import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class QaScooterBaseClient {

    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    protected Response getRequest(String uri) {
        return given().config(config)
                .get(uri);
    }

    protected Response postRequest(String uri, Object payload) {
        return given().config(config)
                .header("Content-Type", "application/json")
                .body(payload)
                .post(uri);
    }

    protected Response deleteRequest(String uri) {
        return given().config(config)
                .delete(uri);
    }

    protected  Response putRequest(String uri, Object payload) {
        return given().config(config)
                .header("Content-Type", "application/json")
                .body(payload)
                .put(uri);
    }

    protected  Response putRequest(String uri) {
        return given().config(config)
                .put(uri);
    }

}
