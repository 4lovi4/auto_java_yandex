package client;

import io.restassured.response.Response;
import models.*;
import java.util.HashMap;
import java.util.List;

public class CourierClient extends QaScooterBaseClient{

    static protected final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    private final String COURIER_ENDPOINT = "/api/v1/courier";
    private final String LOGIN_ENDPOINT = "/api/v1/courier/login";

    public Response postLogin(CourierLogin login) {
        return postRequest(BASE_URL + "/api/v1/courier/login", login);
    }

    public Response postLoginResponse(CourierLogin login) {
        return postRequest(BASE_URL + LOGIN_ENDPOINT, login);
    }

    public Response createCourier(CourierLogin courierLogin) {
        return postRequest(BASE_URL + COURIER_ENDPOINT, courierLogin);
    }

    public Response deleteCourier(Long id) {
        return deleteRequest(BASE_URL + COURIER_ENDPOINT + "/" + id.toString());
    }

    public CourierLogin getCourierOrders(String id) {
        return getRequest(BASE_URL + COURIER_ENDPOINT + id + "/ordersCount")
                .body().as(CourierLogin.class);
    }

    public Response getCourierOrdersResponse(String id) {
        return getRequest(BASE_URL + COURIER_ENDPOINT + id + "/ordersCount");
    }
}
