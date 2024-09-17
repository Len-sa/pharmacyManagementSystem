package entities;

public class EmployeeBill {
    private int employeeId;
    private int billId;

    public EmployeeBill(int employeeId, int billId) {
        this.employeeId = employeeId;
        this.billId = billId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getBillId() {
        return billId;
    }
}
