package entities;

public class Customer {
    private int customerId ;
    private String customerFirstName;
    private String customerMiddleName;
    private String customerLastName = null;
    private Address customerAddress;
    private String customerDOB;

    public Customer(String customerFirstName, String customerMiddleName, String customerDOB, Address customerAddress) {
        this.customerFirstName = customerFirstName;
        this.customerMiddleName = customerMiddleName;
        this.customerDOB = customerDOB;
        this.customerAddress = customerAddress;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerDOB(String customerDOB) {
        this.customerDOB = customerDOB;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerMiddleName() {
        return customerMiddleName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerDOB() {
        return customerDOB;
    }
}
