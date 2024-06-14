package model;

public class Stock {
    private String batchNo;
    private String supplierId;
    private String date;
    private String time;
    private Double cost;

    @Override
    public String toString() {
        return "Stock{" +
                "batchNo='" + batchNo + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Stock() {
    }

    public Stock(String batchNo, String supplierId, String date, String time, Double cost) {
        this.batchNo = batchNo;
        this.supplierId = supplierId;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
