package entities;
import entities.PharmacyPhoneNumber;

import java.util.List;

public class Pharmacy {
    private String PharmacyName;
    private int pharmacyId = 1;
    Address address;
    List<PharmacyPhoneNumber> phoneNumbers;

    public Pharmacy(String pharmacyName,Address address, List<PharmacyPhoneNumber> phoneNumbers){
        this.PharmacyName = pharmacyName;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }
    public String getPharmacyName() {
        return PharmacyName;
    }

    public Address getAddress() {
        return address;
    }

    public List<PharmacyPhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
}
