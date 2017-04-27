package ro.vlad.security;

import javax.persistence.EntityManager;
import java.util.List;

public class UserAccountAction {

    public static boolean exists(EntityManager entityManager, String accountName, String password) {
        UserAccount userAccount = entityManager.find(UserAccount.class, accountName);
        if (userAccount != null && userAccount.getPassword().equals(password)) {
            System.out.println("Valid user, " + accountName + ": " + password);
            return true;}
        else {
            System.out.println("Invalid user " + accountName + ": " + password);
            return false;}}

    public static void add(EntityManager entityManager, String accountName, String name, String email, String password) {
        UserAccount userAccount = new UserAccount();
        try {
            entityManager.getTransaction().begin();
            userAccount.setAccountName(accountName);
            userAccount.setName(name);
            userAccount.setEmail(email);
            userAccount.setPassword(password);
            userAccount = entityManager.merge(userAccount);
            entityManager.getTransaction().commit();}
        catch (Exception e) {
            entityManager.getTransaction().rollback();}}

    public static void list(EntityManager entityManager) {
        try {
            entityManager.getTransaction().begin();
            List<UserAccount> accounts = entityManager.createQuery("from useraccount").getResultList();
            System.out.println("-------------------------------------------------------------------------");
            for (UserAccount userAccount : accounts) {
                System.out.println(userAccount.getAccountName() + " " + userAccount.getName() + " " + userAccount.getPassword());}
            entityManager.getTransaction().commit();}
        catch (Exception e) {
            entityManager.getTransaction().rollback();}}

    public void updatePassword(EntityManager entityManager, String accountName, String password) {
        try {
            entityManager.getTransaction().begin();
            UserAccount userAccount = entityManager.find(UserAccount.class, accountName);
            userAccount.setPassword(password);
            entityManager.getTransaction().commit();}
        catch (Exception e) {
            entityManager.getTransaction().rollback();}}

    public void delete(EntityManager entityManager, String accountName) {
        try {
            entityManager.getTransaction().begin();
            UserAccount userAccount = entityManager.find(UserAccount.class, accountName);
            entityManager.remove(userAccount);
            entityManager.getTransaction().commit();}
        catch (Exception e) {
            entityManager.getTransaction().rollback();}}
}