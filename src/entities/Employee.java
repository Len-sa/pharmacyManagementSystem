package entities;

public class Employee {
    private int employeeId;
    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastName;
    private Address employeAddress;
    private Double salary;

    public Employee(String employeeFirstName, String employeeMiddleName, Address employeAddress, Double salary) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeAddress = employeAddress;
        this.salary = salary;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public Address getEmployeAddress() {
        return employeAddress;
    }

    public Double getSalary() {
        return salary;
    }
}
