package entities;

public class Prescription {
    private int periscriptionId;
    private int customerId;
    private String doctorsFirstName;
    private String doctorsMiddleName;
    private String doctorsLastName;
    private String institutionName;
    private String dateIssued;

    public Prescription(int customerId, String doctorsFirstName, String doctorsMiddleName, String institutionName, String dateIssued) {
        this.customerId = customerId;
        this.doctorsFirstName = doctorsFirstName;
        this.doctorsMiddleName = doctorsMiddleName;
        this.institutionName = institutionName;
        this.dateIssued = dateIssued;
    }

    public void setDoctorsLastName(String doctorsLastName) {
        this.doctorsLastName = doctorsLastName;
    }

    public int getPeriscriptionId() {
        return periscriptionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDoctorsFirstName() {
        return doctorsFirstName;
    }

    public String getDoctorsMiddleName() {
        return doctorsMiddleName;
    }

    public String getDoctorsLastName() {
        return doctorsLastName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getDateIssued() {
        return dateIssued;
    }
}

