package entities;

public class CustomerPhoneNumber {
    private int customerId;
    private String phoneNumber;

    public CustomerPhoneNumber(int customerId, String phoneNumber) {
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
