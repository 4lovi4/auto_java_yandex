package models;

public class CourierOrders {
    private String id;
    private String ordersCount;

    public CourierOrders(String id, String ordersCount) {
        this.id = id;
        this.ordersCount = ordersCount;
    }

    public CourierOrders(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(String ordersCount) {
        this.ordersCount = ordersCount;
    }
}
