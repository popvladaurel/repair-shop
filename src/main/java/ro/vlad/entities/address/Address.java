package ro.vlad.entities.address;

import javax.persistence.*;
//TODO expand Address class to include separate fields for city, street, county and number
@Entity
@Table(name = "addresses")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column (name = "address_id", columnDefinition = "serial") private Long addressId;
    @Column (name = "address") private String address;

    public Address() {}

    public Address(String address) {
        this.address = address;}

    public void setAddressId(Long addressId) {this.addressId = addressId;}
    public void setAddress(String address) {this.address = address;}

    public Long getAddressId() {return addressId;}
    public String getAddress() {return address;}
}
