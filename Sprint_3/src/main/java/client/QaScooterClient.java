package client;

import io.restassured.response.Response;
import models.*;

public class QaScooterClient extends QaScooterBaseClient{

    private final String baseUrl = "http://qa-scooter.praktikum-services.ru/";

    public CourierLogin postLogin(CourierLogin login) {
        return postRequest(baseUrl + "/api/v1/courier/login", login).body()
                .as(CourierLogin.class);
    }

    public Response createCourier(CourierLogin courierLogin) {
        return postRequest(baseUrl + "/api/v1/courier", courierLogin);
    }

    public Response deleteCourier(String id) {
        return deleteRequest(baseUrl + "/" + id);
    }

    public CourierLogin getCourierOrders(String id) {
        return getRequest(baseUrl + "/api/v1/courier/" + id + "/ordersCount")
                .body().as(CourierLogin.class);
    }

}
