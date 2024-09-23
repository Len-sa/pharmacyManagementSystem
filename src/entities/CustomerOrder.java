package entities;

public class CustomerOrder {
    private int orderId;
    private String orderDate;

    private int employeeId;
    private int customerId;

    public CustomerOrder(int employeeId, int customerId) {
        this.employeeId = employeeId;
        this.customerId = customerId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
