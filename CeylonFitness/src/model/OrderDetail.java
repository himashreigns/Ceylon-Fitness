package model;

public class OrderDetail {
    private String orderId;
    private String itemCode;
    private Double unitPrice;
    private int quantity;
    private Double price;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String itemCode, Double unitPrice, int quantity, Double price) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
