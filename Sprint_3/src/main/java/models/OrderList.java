package models;

import java.util.List;

public class OrderList {
    private List<Order> orders;
    private List<MetroStation> availableStations;
    private PageInfo pageInfo;

    public OrderList(List<Order> orders, PageInfo pageInfo, List<MetroStation> availableStations) {
        this.orders = orders;
        this.availableStations = availableStations;
        this.pageInfo = pageInfo;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<MetroStation> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<MetroStation> availableStations) {
        this.availableStations = availableStations;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
