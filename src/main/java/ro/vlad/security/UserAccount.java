package ro.vlad.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraccounts")
public class UserAccount {

    @Id @Column(name = "accountName") private String accountName;
    @Column(name = "name") private String name;
    @Column(name = "email") private String email;
    @Column(name = "password") private String password;

    public void setAccountName(String accountName) {
        this.accountName = accountName;}

    public void setName(String name) {
        this.name = name;}

    public void setEmail(String email) {
        this.email = email;}

    public void setPassword(String password) {
        this.password = password;}

    public String getName() {
        return name;}

    public String getAccountName() {
        return accountName;}

    public String getEmail() {
        return email;}

    public String getPassword() {
        return password;}
}
