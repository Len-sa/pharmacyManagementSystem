package entities;

public class CustomerOrder {
    private int orderId;
    private String orderDate;

    private int employeeId;
    private int customerId;

    public CustomerOrder(int orderId, String orderDate, int employeeId, int customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.employeeId = employeeId;
        this.customerId = customerId;
    }
    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
