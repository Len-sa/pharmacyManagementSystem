package entities;

public class MedicinePrescription {
    private int prescriptionId;
    private int medicineId;
    private int customerId;

    public MedicinePrescription(int prescriptionId, int medicineId, int customerId) {
        this.prescriptionId = prescriptionId;
        this.medicineId = medicineId;
        this.customerId = customerId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
