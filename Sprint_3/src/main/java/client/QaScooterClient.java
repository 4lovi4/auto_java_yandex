package client;

import io.restassured.response.Response;
import models.*;
import java.util.HashMap;
import java.util.List;

public class QaScooterClient extends QaScooterBaseClient{

    private final String baseUrl = "http://qa-scooter.praktikum-services.ru";

    public Response postLogin(CourierLogin login) {
        return postRequest(baseUrl + "/api/v1/courier/login", login);
    }

    public Response postLoginResponse(CourierLogin login) {
        return postRequest(baseUrl + "/api/v1/courier/login", login);
    }

    public Response createCourier(CourierLogin courierLogin) {
        return postRequest(baseUrl + "/api/v1/courier", courierLogin);
    }

    public Response deleteCourier(Long id) {
        return deleteRequest(baseUrl + "/" + id.toString());
    }

    public CourierLogin getCourierOrders(String id) {
        return getRequest(baseUrl + "/api/v1/courier/" + id + "/ordersCount")
                .body().as(CourierLogin.class);
    }

    public Response getCourierOrdersResponse(String id) {
        return getRequest(baseUrl + "/api/v1/courier/" + id + "/ordersCount");
    }

    public Response finishOrder(Long id) {
        return putRequest(baseUrl + "/api/v1/orders/finish/" + id.toString());
    }

    public  Response cancelOrder(Long track) {
        String payload = String.format("{\"track\": %d}", track);
        return putRequest(baseUrl + "/api/v1/orders/cancel", payload);
    }

    public Response listOrders() {
        return getRequest(baseUrl + "/api/v1/orders");
    }

    public Response listOrders(Long courierId) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        return getRequest(baseUrl + "/api/v1/orders", params);
    }

    public Response listOrders(Long courierId, List<String> stationsList) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        params.put("nearestStation", stationsList);
        return getRequest(baseUrl + "/api/v1/orders", params);
    }

    public Response listOrders(Long courierId, List<String> stationsList, Integer limit, Integer page) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        params.put("nearestStation", stationsList);
        params.put("limit", limit);
        params.put("page", page);
        return getRequest(baseUrl + "/api/v1/orders", params);
    }

    public Response listOrders(Long courierId, List<String> stationsList, Integer limit) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        params.put("nearestStation", stationsList);
        params.put("limit", limit);
        return getRequest(baseUrl + "/api/v1/orders", params);
    }

    public Response getOrdersByTrack(Long track) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("t", track);
        return getRequest(baseUrl + "/v1/orders/track", params);
    }

    public Response acceptOrder(Long orderId, Long courierId) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        return putRequest(baseUrl + "/api/v1/orders/accept/" + orderId.toString(), params);
    }

    public Response createOrder(Order order) {
        return postRequest(baseUrl + "/api/v1/orders", order);
    }

}
