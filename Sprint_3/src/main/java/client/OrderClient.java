package client;

import io.restassured.response.Response;
import models.Order;
import java.util.HashMap;
import java.util.List;
import static client.CourierClient.BASE_URL; // Ипользую константу из класса клиента курьера

public class OrderClient extends QaScooterBaseClient{

    private final String ORDERS_ENDPOINT = "/api/v1/orders";
    private final String TRACK_ENDPOINT = "/v1/orders/track";

    public Response finishOrder(Long id) {
        return putRequest(BASE_URL + ORDERS_ENDPOINT + "/finish/" + id.toString());
    }

    public  Response cancelOrder(Long track) {
        String payload = String.format("{\"track\": %d}", track);
        return putRequest(BASE_URL + ORDERS_ENDPOINT + "/cancel", payload);
    }

    public Response listOrders() {
        return getRequest(BASE_URL + ORDERS_ENDPOINT);
    }

    public Response listOrders(Long courierId) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        return getRequest(BASE_URL + ORDERS_ENDPOINT, params);
    }

    public Response listOrders(Long courierId, List<String> stationsList) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        params.put("nearestStation", stationsList);
        return getRequest(BASE_URL + ORDERS_ENDPOINT, params);
    }

    public Response listOrders(Long courierId, List<String> stationsList, Integer limit, Integer page) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        params.put("nearestStation", stationsList);
        params.put("limit", limit);
        params.put("page", page);
        return getRequest(BASE_URL + ORDERS_ENDPOINT, params);
    }

    public Response listOrders(Long courierId, List<String> stationsList, Integer limit) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        params.put("nearestStation", stationsList);
        params.put("limit", limit);
        return getRequest(BASE_URL + ORDERS_ENDPOINT, params);
    }

    public Response getOrdersByTrack(Long track) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("t", track);
        return getRequest(BASE_URL + TRACK_ENDPOINT, params);
    }

    public Response acceptOrder(Long orderId, Long courierId) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("courierId", courierId);
        return putRequest(BASE_URL + ORDERS_ENDPOINT + "/accept/" + orderId.toString(), params);
    }

    public Response createOrder(Order order) {
        return postRequest(BASE_URL + ORDERS_ENDPOINT, order);
    }

}
