package entities;

public class SupplyOrder {
    private int supplyOrderId;
    private int supplierId;
    private String orderDate;

    public SupplyOrder( int supplierId, String orderDate) {
        this.supplierId = supplierId;
        this.orderDate = orderDate;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
