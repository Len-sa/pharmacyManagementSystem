package entities;

public class SupplierPhoneNumber {
    private int supplierId;
    private String phoneNumber;

    public SupplierPhoneNumber(int supplierId,String phoneNumber){
        this.phoneNumber = phoneNumber;
        this.supplierId = supplierId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
