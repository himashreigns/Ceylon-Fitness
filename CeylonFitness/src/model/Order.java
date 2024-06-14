package model;

public class Order {
    private String orderId;
    private String customerId;
    private String date;
    private String time;
    private Double cost;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Order() {
    }

    public Order(String orderId, String customerId, String date, String time, Double cost) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
