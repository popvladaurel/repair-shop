package ro.vlad.entities.userAccount;

import org.json.JSONObject;
import ro.vlad.entities.person.Person;
import ro.vlad.security.PasswordStorage;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

/**
 * Basic UserAccount actions
 */
public class GoogleAccountActions {
    private EntityManager entityManager;

    public GoogleAccountActions(EntityManager entityManager){this.entityManager = entityManager;}

    public void addAccount(JSONObject googleJSON) {
        entityManager.getTransaction().begin();
        GoogleAccount googleAccount = new GoogleAccount();
        googleAccount.setGoogle_id(googleJSON.getString("sub"));
        googleAccount.setEmail(googleJSON.getString("email"));
        googleAccount.setVerified_email(googleJSON.getBoolean("email_verified"));
        googleAccount.setGiven_name(googleJSON.getString("given_name"));
        googleAccount.setFamily_name(googleJSON.getString("family_name"));
        googleAccount.setProfile(googleJSON.getString("profile"));
        googleAccount.setPicture(googleJSON.getString("picture"));
        entityManager.persist(googleAccount);
        entityManager.flush();
        entityManager.getTransaction().commit();}

    List<GoogleAccount> listAccounts() {
        return (List<GoogleAccount>) entityManager.createQuery("SELECT googleAccount FROM ro.vlad.entities.userAccount.GoogleAccount googleAccount").getResultList();}
}