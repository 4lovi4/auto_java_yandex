package client;

import io.restassured.response.Response;
import models.*;

public class QaScooterClient extends QaScooterBaseClient{

    private final String baseUrl = "http://qa-scooter.praktikum-services.ru/";

    public Response postLogin(CourierLogin login) {
        return postRequest(baseUrl + "/api/v1/courier/login", login);
    }
}
