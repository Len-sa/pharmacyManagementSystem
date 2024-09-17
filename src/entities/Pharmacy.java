package entities;

public class Pharmacy {
    private String PharmacyName;
    private int pharmacyId;
    Address address;

    public Pharmacy(String pharmacyName,Address address){
        this.PharmacyName = pharmacyName;
        this.address = address;
    }
    public int getPharmacyId() {
        return pharmacyId;
    }
    public String getPharmacyName() {
        return PharmacyName;
    }
}
