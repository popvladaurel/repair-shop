package ro.vlad.entities.userAccount;

import ro.vlad.entities.person.Person;
import ro.vlad.security.PasswordStorage;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class UserAccountActions {
    private EntityManager entityManager;

    public UserAccountActions (EntityManager entityManager){this.entityManager = entityManager;}

    public void addAccount(String newUserAccountName, String newPassword, Person newPerson) {
        entityManager.getTransaction().begin();
        DeletedUserAccount deletedUserAccount = entityManager.find(DeletedUserAccount.class, newUserAccountName);
        if (deletedUserAccount != null) {
            entityManager.remove(deletedUserAccount);}
        try {
            String newHashedPassword = PasswordStorage.createHash(newPassword);
            UserAccount newUserAccount = new UserAccount();
            newUserAccount.setAccountId(newUserAccountName);
            newUserAccount.setPasswordHash(newHashedPassword);
            newUserAccount.setPerson(newPerson);
            entityManager.persist(newUserAccount);
            entityManager.flush();
            entityManager.getTransaction().commit();}
        catch (PasswordStorage.CannotPerformOperationException e) {e.printStackTrace();}}

    public List<UserAccount> listAccounts() {
        List<UserAccount> accountsList = entityManager.createQuery("SELECT userAccount FROM ro.vlad.entities.userAccount.UserAccount userAccount").getResultList();
        return accountsList;}

    public void changePassword(String accountName, String newPassword) throws PasswordStorage.CannotPerformOperationException {
        entityManager.getTransaction().begin();
        UserAccount existingUserAccount = entityManager.find(UserAccount.class, accountName);
        String newPasswordHash = null;
        try {
            newPasswordHash = PasswordStorage.createHash(newPassword);}
        catch (PasswordStorage.CannotPerformOperationException e) {e.printStackTrace();}
        existingUserAccount.setPasswordHash(newPasswordHash);
        entityManager.merge(existingUserAccount);
        entityManager.flush();
        entityManager.getTransaction().commit();
        System.out.println("Account modified!");}

    public void deleteAccount(String accountName) {
        entityManager.getTransaction().begin();
        UserAccount userAccount = getUserAccountByAccountName(accountName);
        if (userAccount != null) {
            DeletedUserAccount deletedUserAccount = new DeletedUserAccount();
            deletedUserAccount.setAccountName(accountName);
            deletedUserAccount.setDateDeleted(new Timestamp(System.currentTimeMillis()));
            entityManager.persist(deletedUserAccount);
            entityManager.remove(userAccount);
            entityManager.flush();}
        entityManager.getTransaction().commit();}

    public UserAccount getUserAccountByAccountName(String accountName) {
        UserAccount userAccount = entityManager.find(UserAccount.class, accountName);
        return userAccount;}

    public boolean userAccountWasDeleted (String accountName) {
        DeletedUserAccount deletedUserAccount = entityManager.find(DeletedUserAccount.class, accountName);
        if (deletedUserAccount != null) {return true;}
        else {return false;}}
}