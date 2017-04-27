package ro.vlad.entities.client;

import ro.vlad.entities.Client;

public class Company extends Client {
    private String name;
    private String CUI;
    private String IBAN;
    private Person delegate;

    //company with name, CUI, IBAN, contactDetails, Address and delegate
    public Company(String name, String CUI, String IBAN, String phoneNumber, String email,
                   String country, String county, String city, String street, String number, String postalCode,
                   String firstName, String lastName, String CNP, String delegatePhoneNumber, String delegateEmail) {
        super(phoneNumber, email, country, county, city, street, number, postalCode);
        this.name = name;
        this.CUI = CUI;
        this.IBAN = IBAN;
        this.delegate = new Person(firstName, lastName, CNP, delegatePhoneNumber, delegateEmail);
    }

    //company without delegate
    public Company(String name, String CUI, String IBAN, String phoneNumber, String email, String country, String county, String city, String street, String number, String postalCode) {
        super(phoneNumber, email, country, county, city, street, number, postalCode);
        this.name = name;
        this.CUI = CUI;
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return CUI;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getDelegateContactDetails() {
        return delegate.getId();
    }
}
