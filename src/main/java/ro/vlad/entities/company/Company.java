package ro.vlad.entities.company;

import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.address.Address;
import ro.vlad.entities.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id @Column (name = "cui") private String CUI;
    @Column (name = "j") private String j;
    @Column (name = "name") private String name;
    @Column (name = "iban") private String IBAN;
    @OneToOne (cascade = CascadeType.ALL) private Address address;
    @OneToOne (cascade = CascadeType.ALL) private ContactDetails contactDetails;

    public Company() {}

    Company(String CUI, String j, String name, String IBAN) {
        this.CUI = CUI;
        this.j = j;
        this.name = name;
        this.IBAN = IBAN;}

    public void setCUI(String CUI) {this.CUI = CUI;}
    public void setJ(String j) {this.j = j;}
    public void setName(String name) {this.name = name;}
    public void setIBAN(String IBAN) {this.IBAN = IBAN;}
    public void setAddress(Address address) {this.address = address;}
    public void setContactDetails(ContactDetails contactDetails) {this.contactDetails = contactDetails;}

    public String getCUI() {return CUI;}
    public String getJ() {return j;}
    public String getName() {return name;}
    public String getIBAN() {return IBAN;}
    public Address getAddress() {return address;}
    public ContactDetails getContactDetails() {return contactDetails;}
}
