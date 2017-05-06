package ro.vlad.entities.userAccount;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import ro.vlad.entities.person.Person;

import javax.persistence.*;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="userAccount")
@Table(name = "useraccounts")
public class UserAccount {
    @Id @Column (name = "account_id") private String accountId;
    @Column (name = "passwordHash") private String passwordHash;
    @OneToOne (cascade = CascadeType.ALL) private Person person;

    public UserAccount() {}

    public UserAccount(String accountId, String passwordHash, Person person) {
        this.accountId = accountId;
        this.passwordHash = passwordHash;
        this.person = person;}

    void setAccountId(String accountName) {this.accountId = accountName;}
    void setPasswordHash(String passwordHash) {this.passwordHash = passwordHash;}
    void setPerson(Person person) {this.person = person;}

    public String getAccountId() {return accountId;}
    public String getPasswordHash() {return passwordHash;}
    public Person getPerson() {return person;}
}
