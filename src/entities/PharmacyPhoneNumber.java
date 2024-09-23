package entities;

public class PharmacyPhoneNumber {
    private int pharmacyId;
    private String phoneNumber;

    public PharmacyPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PharmacyPhoneNumber(int pharmacyId, String phoneNumber){
        this.pharmacyId = pharmacyId;
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public String getPhoneNumber() {
        return phoneNumber;

    }



}
