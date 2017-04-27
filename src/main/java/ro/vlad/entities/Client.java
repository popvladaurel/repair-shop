package ro.vlad.entities;

import ro.vlad.entities.client.Address;
import ro.vlad.entities.client.ContactDetails;

public abstract class Client {
    private ContactDetails contactDetails;
    private Address address;

    //client with phone number, email, and address
    public Client(String phoneNumber, String email, String country, String county, String city, String street, String number, String postalCode) {
        this.contactDetails = new ContactDetails(phoneNumber, email);
        this.address = new Address(country, county, city, street, number, postalCode);
    }

    //client with only phone number and email
    public Client(String phoneNumber, String email) {
        this.contactDetails = new ContactDetails(phoneNumber, email);
    }

    public abstract String getName();

    public abstract String getId();
}
