package entities;

import java.util.List;

public class Employee {
    private int employeeId;
    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastName;
    private Address employeAddress;
    List<PharmacyPhoneNumber> phoneNumbers;
    private Double salary;


    public Employee(String employeeFirstName, String employeeMiddleName, Address employeAddress, Double salary,List<PharmacyPhoneNumber> phoneNumbers) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeAddress = employeAddress;
        this.salary = salary;
        this.phoneNumbers = phoneNumbers;
    }
    public Employee(String employeeFirstName, String employeeMiddleName, Address employeAddress, Double salary) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeAddress = employeAddress;
        this.salary = salary;
    }

    public Employee(String employeeFirstName, String employeeMiddleName, Address employeAddress) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeAddress = employeAddress;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }



    public  List<PharmacyPhoneNumber> getEmployeePhoneNumber() {
        return phoneNumbers;
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
