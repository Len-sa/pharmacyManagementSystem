package entities;

public class Bill {
    private int billId;
    private int orderId;
    private String billDay;
    private double totalMoney;
    private double Paid;

    public Bill(int orderId, String billDay, double totalMoney, double paid) {
        this.orderId = orderId;
        this.billDay = billDay;
        this.totalMoney = totalMoney;
        Paid = paid;
    }

    public int getBillId() {
        return billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getBillDay() {
        return billDay;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public double getPaid() {
        return Paid;
    }
}
