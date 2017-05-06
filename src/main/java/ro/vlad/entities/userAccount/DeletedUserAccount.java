package ro.vlad.entities.userAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "deleteduseraccounts")
public class DeletedUserAccount {
    @Id @Column(name = "accountname") private String accountName;
    @Column(name = "datedeleted") private Timestamp dateDeleted;

    public void setAccountName(String accountName) {this.accountName = accountName;}
    public void setDateDeleted(Timestamp dateDeleted) {this.dateDeleted = dateDeleted;}

    public String getAccountName() {return accountName;}
    public Timestamp getDateDeleted() {return dateDeleted;}
}
