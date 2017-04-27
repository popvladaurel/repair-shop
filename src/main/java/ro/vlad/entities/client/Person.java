package ro.vlad.entities.client;

import ro.vlad.entities.Client;

public class Person extends Client {
    private String firstName;
    private String lastName;
    private String CNP;

    //person with name, CNP, contactDetails and Address
    public Person(String firstName, String lastName, String CNP, String phoneNumber, String email, String country, String county, String city, String street, String number, String postalCode) {
        super(phoneNumber, email, country, county, city, street, number, postalCode);
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
    }

    //person withot Address
    public Person(String firstName, String lastName, String CNP, String phoneNumber, String email) {
        super(phoneNumber, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getId() {
        return CNP;
    }
}
