package ro.vlad.entities.person;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id @Column (name = "cnp") private String CNP;
    @Column (name ="name") private String name;
    @OneToOne (cascade = CascadeType.ALL) private Address address;
    @OneToOne (cascade = CascadeType.ALL) private ContactDetails contactDetails;

    public void setCNP(String CNP) {this.CNP = CNP;}
    public void setName(String name) {this.name = name;}
    public void setAddress(Address address) {this.address = address;}
    public void setContactDetails(ContactDetails contactDetails) {this.contactDetails = contactDetails;}

    public String getCNP() {return CNP;}
    public String getName() {return name;}
    public Address getAddress() {return address;}
    public ContactDetails getContactDetails() {return contactDetails;}
}
