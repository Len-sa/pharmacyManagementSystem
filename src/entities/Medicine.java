package entities;

public class Medicine {
    private int medicineId;
    private String medicineName;
    private  String medicineBrand;
    private String manufacturedDate;
    private String expiredDate;
    private double sellingPrice;
    private int stockQuantity;

public Medicine(String medicineName,String medicineBrand,String manufacturedDate,String expiredDate,double sellingPrice,int stockQuantity){
    this.medicineName = medicineName;
    this.medicineBrand = medicineBrand;
    this.manufacturedDate = manufacturedDate;
    this.expiredDate = expiredDate;
    this.sellingPrice = sellingPrice;
    this.stockQuantity = stockQuantity;
}

    public int getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineBrand() {
        return medicineBrand;
    }

    public String getManufacturedDate() {
        return manufacturedDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

}
