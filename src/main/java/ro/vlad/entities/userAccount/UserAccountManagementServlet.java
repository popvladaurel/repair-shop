package ro.vlad.entities.userAccount;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.person.Person;
import ro.vlad.security.LogoutServlet;
import ro.vlad.security.PasswordStorage;
import sun.security.util.DisabledAlgorithmConstraints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;

@WebServlet(urlPatterns = "/userAccountManagementServlet", name = "userAccountManagementServlet")
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
            case "addAccount1":
                req.setAttribute("pathToServlet", "/userAccountManagementServlet?action=addAccount2");
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmButton", "Add User");
                req.setAttribute("pageToShowInTheMainBody", "/jsp/userAccount.jsp");
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;
            case "addAccount2":
                ContactDetails contactDetails = new ContactDetails(req.getParameter("newPhoneNumber"), req.getParameter("newEmail"));
                Address address = new Address(req.getParameter("newAddress"));
                Person person = new Person(req.getParameter("newCNP"), req.getParameter("newName"), address, contactDetails);
                UserAccountDeleted userAccountDeleted = entityManager.find(UserAccountDeleted.class, req.getParameter("newAccountName"));
                if (userAccountDeleted != null) {
                    req.setAttribute("modalMessage", "This user was invalidated, and cannot be created again.");
                    req.setAttribute("modalShow", "block");
                    req.setAttribute("pageToShowInTheMainBody", null);
                    getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
                else {
                    userAccountActions.addAccount(req.getParameter("newAccountName"), req.getParameter("newPassword"), person);
                    req.setAttribute("modalMessage", "Account added successfully!");
                    req.setAttribute("modalShow", "block");
                    req.setAttribute("pageToShowInTheMainBody", null);
                    getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
                break;
            case "changePassword":
                String userName = (String) req.getSession().getAttribute("authenticatedUser");
                String newPassword = req.getParameter("newPassword");
                userAccountActions.changePassword(userName, newPassword);
                req.setAttribute("modalMessage", "Password changed! Login using your new password.");
                req.setAttribute("modalShow", "block");
                req.setAttribute("pageToShowInTheMainBody", null);
                req.setAttribute("authenticatedUser", null);
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                entityManagerFactory.getCache().evictAll();
                LogoutServlet.clearSession(req, resp);
                break;
            case "deleteAccount":
                userName = (String) req.getSession().getAttribute("authenticatedUser");
                System.out.println(userName + " will be deleted now.");
                userAccountActions.deleteAccount(userName);
                req.setAttribute("modalMessage", "Account deleted and invalidated.");
                req.setAttribute("modalShow", "block");
                req.setAttribute("pageToShowInTheMainBody", null);
                LogoutServlet.clearSession(req, resp);
                break;
            default:
                List<UserAccount> userAccountsList = userAccountActions.listAccounts();
                req.setAttribute("useraccounts", userAccountsList);
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}}
}
