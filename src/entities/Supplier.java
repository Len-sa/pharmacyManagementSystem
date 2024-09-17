package entities;

public class Supplier {
private int supplierId;
private String supplierName;
private Address address;

    public Supplier(int supplierId, String supplierName,Address address) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.address = address;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public Address getAddress() {
        return address;
    }
}
