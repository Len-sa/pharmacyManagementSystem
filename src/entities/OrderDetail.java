package entities;

public class OrderDetail {
    private int orderId;
    private int medicineId;
    private int quantity;
    private double pricePerItem;
    private double discount;

    public OrderDetail(int orderId, int medicineId, int quantity, double pricePerItem) {
        this.orderId = orderId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public double getDiscount() {
        return discount;
    }
}
