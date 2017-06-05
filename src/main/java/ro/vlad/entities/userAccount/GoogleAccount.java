package ro.vlad.entities.userAccount;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import ro.vlad.entities.person.Person;

import javax.persistence.*;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="userAccount")
@Table(name = "googleaccounts")
public class GoogleAccount {
    @Id @Column(name= "google_id") String google_id;
    @Column (name = "email") String email;
    @Column (name = "verified_email") boolean verified_email;
    @Column (name = "name") String name;
    @Column (name = "given_name") String given_name;
    @Column (name = "family_name") String family_name;
    @Column (name = "profile") String profile;
    @Column (name = "picture") String picture;

    public GoogleAccount() {}

    public GoogleAccount(String google_id, String email, boolean verified_email,
                         String name, String given_name, String family_name,
                         String profile, String picture) {
        this.google_id = google_id;
        this.email = email;
        this.verified_email = verified_email;
        this.name = name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.profile = profile;
        this.picture = picture;}

    public void setGoogle_id(String google_id) {this.google_id = google_id;}
    public void setEmail(String email) {this.email = email;}
    public void setVerified_email(boolean verified_email) {this.verified_email = verified_email;}
    public void setName(String name) {this.name = name;}
    public void setGiven_name(String given_name) {this.given_name = given_name;}
    public void setFamily_name(String family_name) {this.family_name = family_name;}
    public void setProfile(String profile) {this.profile = profile;}
    public void setPicture(String picture) {this.picture = picture;}

    public String getGoogle_id() {return google_id;}
    public String getEmail() {return this.email;}
    public boolean isVerified_email() {return this.verified_email;}
    public String getName() {return this.name;}
    public String getGiven_name() {return this.given_name;}
    public String getFamily_name() {return this.family_name;}
    public String getProfile() {return profile;}
    public String getPicture() {return picture;}

    public String toString() {
        return "GoogleAccount [id=" + this.google_id +
            ", email=" + this.email +
            ", verified_email=" + this.verified_email +
            ", name=" + this.name +
            ", given_name=" + this.given_name +
            ", family_name=" + this.family_name + "]";}
}
