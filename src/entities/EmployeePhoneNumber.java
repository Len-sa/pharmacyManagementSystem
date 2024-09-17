package entities;

public class EmployeePhoneNumber {
    private int employeeId;
    private String phoneNumber;

    public EmployeePhoneNumber(int employeeId, String phoneNumber) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
