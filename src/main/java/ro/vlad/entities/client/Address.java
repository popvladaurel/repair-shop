package ro.vlad.entities.client;

public class Address {
    private String country;
    private String county;
    private String city;
    private String street;
    private String number;
    private String postalCode;

    public Address(String country, String county, String city, String street, String number, String postalCode) {
        this.country = country;
        this.county = county;
        this.city = city;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return "\n Country: " + country +
                "\n County: " + county +
                "\n City: " + city +
                "\n Street: " + street +
                "\n Number: " + number +
                "\n Postal Code: " + postalCode;
    }
}
