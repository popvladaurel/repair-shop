package ro.vlad.entities.userAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Entities of this type are persisted whenever a UserAccount is deleted
 */
@Entity
@Table(name = "deleteduseraccounts")
public class UserAccountDeleted {
    @Id @Column(name = "accountname") private String accountName;
    @Column(name = "datedeleted") private Timestamp dateDeleted;

    public UserAccountDeleted() {}

    public UserAccountDeleted(String accountName, Timestamp dateDeleted) {
        this.accountName = accountName;
        this.dateDeleted = dateDeleted;}

    void setAccountName(String accountName) {this.accountName = accountName;}
    void setDateDeleted(Timestamp dateDeleted) {this.dateDeleted = dateDeleted;}

    public String getAccountName() {return accountName;}
    public Timestamp getDateDeleted() {return dateDeleted;}
}
