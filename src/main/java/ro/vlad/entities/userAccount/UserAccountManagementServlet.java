package ro.vlad.entities.userAccount;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.person.Person;
import ro.vlad.security.LogoutServlet;
import ro.vlad.security.PasswordStorage;

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

@WebServlet(urlPatterns = "/userAccountManagementServlet")
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
                req.setAttribute("pathtoservlet", "/userAccountManagementServlet?action=addAccount2");
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmbutton", "Add User");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/userAccount.jsp");
                dispatcher.forward(req, resp);
                break;
            case "addAccount2":
                ContactDetails newContactDetails = new ContactDetails();
                newContactDetails.setPhoneNumber(req.getParameter("newphonenumber"));
                newContactDetails.setEmail(req.getParameter("newemail"));
                Address newAddress = new Address();
                newAddress.setAddress(req.getParameter("newaddress"));
                Person newPerson = new Person();
                newPerson.setCNP(req.getParameter("newcnp"));
                newPerson.setName(req.getParameter("newname"));
                newPerson.setContactDetails(newContactDetails);
                newPerson.setAddress(newAddress);
                userAccountActions.addAccount(req.getParameter("newaccountname"), req.getParameter("newpassword"), newPerson);
                resp.sendRedirect("home.jsp");
                break;
            case "editAccountInformation1":
                Person personToEdit = userAccountActions.getUserAccountByAccountName((String) req.getSession().getAttribute("authenticatedUser")).getPerson();
                req.setAttribute("newname", personToEdit.getName());
                req.setAttribute("newaddress", personToEdit.getAddress().getAddress());
                req.setAttribute("newphonenumber", personToEdit.getContactDetails().getPhoneNumber());
                req.setAttribute("newemail", personToEdit.getContactDetails().getEmail());
                req.setAttribute("pathtoservlet", "/userAccountManagementServlet?action=editAccountInformation2");
                req.setAttribute("show", "none");
                req.setAttribute("disabled", "disabled");
                req.setAttribute("confirmbutton", "Update Profile");
                dispatcher = req.getRequestDispatcher("/jsp/userAccount.jsp");
                dispatcher.forward(req, resp);
            case "editAccountInformation2":
                entityManager.getTransaction().begin();
                UserAccount userAccountToEdit = entityManager.find(UserAccount.class, (String) req.getSession().getAttribute("authenticatedUser"));
                userAccountToEdit.getPerson().setName(req.getParameter("newname"));
                userAccountToEdit.getPerson().getAddress().setAddress(req.getParameter("newaddress"));
                userAccountToEdit.getPerson().getContactDetails().setPhoneNumber(req.getParameter("newphonenumber"));
                userAccountToEdit.getPerson().getContactDetails().setEmail(req.getParameter("newemail"));
                entityManager.merge(userAccountToEdit);
                entityManager.flush();
                entityManager.getTransaction().commit();
                req.setAttribute("modalmessage", "Account updated successfully!");
                req.setAttribute("modalshow", "block");
                dispatcher = req.getRequestDispatcher("home.jsp");
                dispatcher.forward(req, resp);
                break;
            case "changePassword":
                String userName = (String) req.getSession().getAttribute("authenticatedUser");
                String newPassword = req.getParameter("newpassword");
                try {
                    userAccountActions.changePassword(userName, newPassword);}
                catch (PasswordStorage.CannotPerformOperationException e) {e.printStackTrace();}
                entityManagerFactory.getCache().evictAll();
                LogoutServlet.clearSession(req, resp);
                break;
            case "deleteAccount":
                userName = (String) req.getSession().getAttribute("authenticatedUser");
                System.out.println(userName + "will be deleted now.");
                userAccountActions.deleteAccount(userName);
                LogoutServlet.clearSession(req, resp);
                break;
            default:
                List<UserAccount> userAccountsList = userAccountActions.listAccounts();
                req.setAttribute("useraccounts", userAccountsList);
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}}
}
