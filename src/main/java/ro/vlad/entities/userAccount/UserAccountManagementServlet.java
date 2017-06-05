package ro.vlad.entities.userAccount;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.person.Person;
import ro.vlad.utils.Modal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;
import static ro.vlad.utils.Colors.*;
import static ro.vlad.utils.Modal.setMessage;

/**
 * Controller for the UserAccount class.
 */
@WebServlet(urlPatterns = "/userAccountManagementServlet", name = "Manage User Accounts")
public class UserAccountManagementServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserAccountActions userAccountActions;

    @Override
    public void init() throws ServletException {
        super.init();
        entityManagerFactory = (EntityManagerFactory) getServletContext().getAttribute(PERSISTENCE_FACTORY);
        entityManager = entityManagerFactory.createEntityManager();
        userAccountActions = new UserAccountActions(entityManager);}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        LOGGER.info("GET command received: " + action);
        action = (action != null) ? action : "list";
        switch (action) {
            case "addAccount":
                LOGGER.info("Displaying userAccount.jsp inside home.jsp and activating buttons...");
                req.setAttribute("pathToServlet", "/userAccountManagementServlet?action=addAccount");
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmButton", "Add User");
                req.setAttribute("pageToShowInTheMainBody", "/WEB-INF/jsp/userAccount/userAccount.jsp");
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                break;}}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        LOGGER.info("POST command received: " + action);
        switch (action) {
            case "addAccount":
                LOGGER.info("Trying to add a new account...");
                ContactDetails contactDetails = new ContactDetails(req.getParameter("newPhoneNumber"), req.getParameter("newEmail"));
                Address address = new Address(req.getParameter("newAddress"));
                Person person = new Person(req.getParameter("newCNP"), req.getParameter("newName"), address, contactDetails);
//TODO no more find.
                UserAccountDeleted userAccountDeleted = entityManager.find(UserAccountDeleted.class, req.getParameter("newAccountName"));
                if (userAccountDeleted != null) {
                    setMessage(req, new Modal(RED, "This user was invalidated, and cannot be recreated.", null));
                    LOGGER.warn("Failed to add already deleted account.");
//TODO implement option to restore account
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);}
                else {
                    userAccountActions.addAccount(req.getParameter("newAccountName"), req.getParameter("newPassword"), person);
                    setMessage(req, new Modal(GREEN, "Account added successfully!", null));
                    LOGGER.info("New account added successfully!");
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);}
                break;
            case "changePassword":
                LOGGER.info("Trying to change password...");
                String userName = (String) req.getSession().getAttribute("authenticatedUser");
                String newPassword = req.getParameter("newPassword");
//TODO implement confirmation method ("Are you sure you want to change your password?")
                userAccountActions.changePassword(userName, newPassword);
                entityManagerFactory.getCache().evictAll();
                req.getSession().setAttribute("authenticatedUser", null);
                setMessage(req, new Modal(GREEN, "Password changed! Login using your new password.", null));
                LOGGER.info("Password changed! Session invalidated.");
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                break;
            case "deleteAccount":
                LOGGER.info("Trying to delete account...");
                userName = (String) req.getSession().getAttribute("authenticatedUser");
                switch (userName) {
                    case "admin":
                        setMessage(req, new Modal(RED, "This account cannot be deleted!", null));
                        LOGGER.warn("Cannot delete admin account!");
                        getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                        break;
                    default:
//TODO Implement confirmation method
                        userAccountActions.deleteAccount(userName);
                        req.getSession().setAttribute("authenticatedUser", null);
                        setMessage(req, new Modal(RED, "Account deleted and invalidated.", null));
                        entityManagerFactory.getCache().evictAll();
                        LOGGER.info("Account deleted! Session invalidated.");
                        getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                        break;}
            default:
                List<UserAccount> userAccountsList = userAccountActions.listAccounts();
                req.setAttribute("useraccounts", userAccountsList);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);}}
}
