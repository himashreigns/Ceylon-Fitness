package model;

public class StockDetail {
    private String batchNo;
    private String itemCode;
    private String expireDate;
    private Double unitPrice;
    private int quantity;
    private Double price;

    @Override
    public String toString() {
        return "StockDetail{" +
                "batchNo='" + batchNo + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public StockDetail() {
    }

    public StockDetail(String batchNo, String itemCode, String expireDate, Double unitPrice, int quantity, Double price) {
        this.batchNo = batchNo;
        this.itemCode = itemCode;
        this.expireDate = expireDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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
