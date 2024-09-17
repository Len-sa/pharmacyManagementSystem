package entities;

public class MedicineSupplier {
    private int supplierId;
    private int medicineId;
    private String supplyDay;

    public MedicineSupplier(int supplierId, int medicineId, String supplyDay) {
        this.supplierId = supplierId;
        this.medicineId = medicineId;
        this.supplyDay = supplyDay;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public String getSupplyDay() {
        return supplyDay;
    }
}
