package ro.vlad.entities.userAccount;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.person.Person;
import ro.vlad.utils.ModalMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;
import static ro.vlad.utils.ModalMessage.Color.*;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        switch (action) {
            case "addAccount":
                ContactDetails contactDetails = new ContactDetails(req.getParameter("newPhoneNumber"), req.getParameter("newEmail"));
                Address address = new Address(req.getParameter("newAddress"));
                Person person = new Person(req.getParameter("newCNP"), req.getParameter("newName"), address, contactDetails);
                UserAccountDeleted userAccountDeleted = entityManager.find(UserAccountDeleted.class, req.getParameter("newAccountName"));
                if (userAccountDeleted != null) {
                    setReqModalMessage(req, new ModalMessage(RED, "This user was invalidated, and cannot be recreated.", null));
//TODO implement option to restore account
                    getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
                else {
                    userAccountActions.addAccount(req.getParameter("newAccountName"), req.getParameter("newPassword"), person);
                    setReqModalMessage(req, new ModalMessage(GREEN, "Account added successfully!", null));
                    getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
                break;
            case "changePassword":
                String userName = (String) req.getSession().getAttribute("authenticatedUser");
                String newPassword = req.getParameter("newPassword");
//TODO implement confirmation method ("Are you sure you want to change your password?")
                userAccountActions.changePassword(userName, newPassword);
                entityManagerFactory.getCache().evictAll();
                req.getSession().setAttribute("authenticatedUser", null);
                setReqModalMessage(req, new ModalMessage(GREEN, "Password changed! Login using your new password.", null));
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;
            case "deleteAccount":
                userName = (String) req.getSession().getAttribute("authenticatedUser");
                switch (userName) {
                    case "admin":
                        setReqModalMessage(req, new ModalMessage(RED, "This account cannot be deleted!", null));
                        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                        break;
                    default:
//TODO Implement confirmation method
                        userAccountActions.deleteAccount(userName);
                        req.getSession().setAttribute("authenticatedUser", null);
                        setReqModalMessage(req, new ModalMessage(RED, "Account deleted and invalidated.", null));
                        entityManagerFactory.getCache().evictAll();
                        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                        break;}
            default:
                List<UserAccount> userAccountsList = userAccountActions.listAccounts();
                req.setAttribute("useraccounts", userAccountsList);
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        switch (action) {
            case "addAccount":
                req.setAttribute("pathToServlet", "/userAccountManagementServlet?action=addAccount");
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmButton", "Add User");
                req.setAttribute("pageToShowInTheMainBody", "/jsp/userAccount.jsp");
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;}}
}
