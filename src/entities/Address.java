package entities;

public class Address {
    private int Kebele;

    private String city;
    private String subcity;

    public Address(int kebele, String city, String subcity) {
        this.city = city;
        this.Kebele = kebele;
        this.subcity = subcity;

    }

    public int getKebele() {
        return Kebele;
    }

    public String getCity() {
        return city;
    }

    public String getSubcity() {
        return subcity;
    }
}
