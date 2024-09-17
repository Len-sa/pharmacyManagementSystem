package entities;

public class PharmacyPhoneNumber {
    private int pharmacyId;
    private String phoneNumber;

    public PharmacyPhoneNumber(int pharmacyId,String phoneNumber){
        this.pharmacyId = pharmacyId;
        this.phoneNumber = phoneNumber;

    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
