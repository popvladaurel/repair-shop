package ro.vlad.entities.userAccount;

import ro.vlad.entities.person.Person;
import ro.vlad.security.PasswordStorage;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class UserAccountActions {
    private EntityManager entityManager;

    public UserAccountActions (EntityManager entityManager){this.entityManager = entityManager;}

    void addAccount(String userAccountName, String password, Person person) {
        try {
            entityManager.getTransaction().begin();
            String newHashedPassword = PasswordStorage.createHash(password);
            UserAccount userAccount = new UserAccount();
            userAccount.setAccountId(userAccountName);
            userAccount.setPasswordHash(newHashedPassword);
            userAccount.setPerson(person);
            entityManager.persist(userAccount);
            entityManager.flush();
            entityManager.getTransaction().commit();}
        catch (PasswordStorage.CannotPerformOperationException e) {e.printStackTrace();}}

    List<UserAccount> listAccounts() {
        return (List<UserAccount>) entityManager.createQuery("SELECT userAccount FROM ro.vlad.entities.userAccount.UserAccount userAccount").getResultList();}

    void changePassword(String accountName, String password) {
        entityManager.getTransaction().begin();
        UserAccount userAccount = entityManager.find(UserAccount.class, accountName);
        try {
            userAccount.setPasswordHash(PasswordStorage.createHash(password));}
        catch (PasswordStorage.CannotPerformOperationException e) {e.printStackTrace();}
        entityManager.merge(userAccount);
        entityManager.flush();
        entityManager.getTransaction().commit();}

    void deleteAccount(String accountName) {
        entityManager.getTransaction().begin();
        UserAccount userAccount = getUserAccountByAccountName(accountName);
        if (userAccount != null) {
            UserAccountDeleted userAccountDeleted = new UserAccountDeleted();
            userAccountDeleted.setAccountName(accountName);
            userAccountDeleted.setDateDeleted(new Timestamp(System.currentTimeMillis()));
            entityManager.persist(userAccountDeleted);
            entityManager.remove(userAccount);
            entityManager.flush();}
        entityManager.getTransaction().commit();}

    public UserAccount getUserAccountByAccountName(String accountName) {
        return entityManager.find(UserAccount.class, accountName);}

    public boolean userAccountWasDeleted (String accountName) {
        UserAccountDeleted userAccountDeleted = entityManager.find(UserAccountDeleted.class, accountName);
        return userAccountDeleted != null;}
}