package ro.vlad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserData")
public class Account {

    @Column(name = "username")
    @Id
    private String username;
    @Column(name = "pass")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username;
    }
}
