package entities;

public class SupplyOrderDetail {
    private int supplyOrderId;
    private int medicineId;
    private int quantity;
    private double price;

    public SupplyOrderDetail(int supplyOrderId, int medicineId, int quantity, double price) {
        this.supplyOrderId = supplyOrderId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getSupplyOrderId() {
        return supplyOrderId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
