package entities;

public class EmployeePosition {
    private int positionId;
    private int employeeId;

    public EmployeePosition(int positionId, int employeeId) {
        this.positionId = positionId;
        this.employeeId = employeeId;
    }

    public int getPositionId() {
        return positionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}
