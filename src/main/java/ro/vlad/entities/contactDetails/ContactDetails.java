package ro.vlad.entities.contactDetails;

import javax.persistence.*;

@Entity
@Table (name = "contactdetails")
public class ContactDetails {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column (name = "contact_id", columnDefinition = "serial") private Long contactDetailsId;
    @Column (name = "phonenumber") private String phoneNumber;
    @Column (name = "email") private String email;

    public ContactDetails() {}

    public ContactDetails(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;}

    public void setContactDetailsId(Long contactDetailsId) {this.contactDetailsId = contactDetailsId;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setEmail(String email) {this.email = email;}

    public Long getContactDetailsId() {return contactDetailsId;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getEmail() {return email;}
}
