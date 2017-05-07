package ro.vlad.entities.person;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.userAccount.UserAccount;
import ro.vlad.entities.userAccount.UserAccountActions;
import ro.vlad.utils.ModalMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;
import static ro.vlad.utils.ModalMessage.Color.*;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

@WebServlet(urlPatterns = "/personManagementServlet", name = "personManagementServlet")
public class PersonManagementServlet extends HttpServlet {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserAccountActions userAccountActions;
    private PersonActions personActions;

    @Override
    public void init() throws ServletException {
        super.init();
        entityManagerFactory = (EntityManagerFactory) getServletContext().getAttribute(PERSISTENCE_FACTORY);
        entityManager = entityManagerFactory.createEntityManager();
        userAccountActions = new UserAccountActions(entityManager);
        personActions = new PersonActions(entityManager);}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        switch (action) {
            case "addCustomer":
                req.setAttribute("pathToServlet", "/personManagementServlet?action=addCustomer");
                setReqModalMessage(req, new ModalMessage(BLUE, "Input all the details and press the \"Add Customer\" button.", "/jsp/addPerson.jsp"));
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;
//TODO Move editing account information to userAccountManagementServlet and replace it with personal information editing
            case "editAccountInformation":
                switch ((String) req.getSession().getAttribute("authenticatedUser")) {
                    case "admin":
                        setReqModalMessage(req, new ModalMessage(RED, "This account cannot be edited!", null));
                        break;
                    default:
                        Person person = userAccountActions.getUserAccountByAccountName((String) req.getSession().getAttribute("authenticatedUser")).getPerson();
                        req.setAttribute("newName", person.getName());
                        req.setAttribute("newAddress", person.getAddress().getAddress());
                        req.setAttribute("newPhoneNumber", person.getContactDetails().getPhoneNumber());
                        req.setAttribute("newEmail", person.getContactDetails().getEmail());
                        req.setAttribute("pathToServlet", "/personManagementServlet?action=editAccountInformation");
                        req.setAttribute("show", "none");
                        req.setAttribute("disabled", "disabled");
                        req.setAttribute("confirmButton", "Update Profile");
                        req.setAttribute("pageToShowInTheMainBody", "/jsp/userAccount.jsp");
                        break;}
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}}
//TODO implement list and delete

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";
        switch (action) {
            case "addCustomer":
                String CNP = req.getParameter("newCNP") ;
                String name = req.getParameter("newName");
                String email = req.getParameter("newEmail");
                String phoneNumber = req.getParameter("newPhoneNumber");
                Address address = new Address(req.getParameter("newAddress"));
                ContactDetails contactDetails = new ContactDetails(phoneNumber, email);
                Person person = new Person(CNP, name, address, contactDetails);
                personActions.addPerson(person);
                setReqModalMessage(req, new ModalMessage(GREEN, "New customer added!", null));
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;
//TODO This too belongs in userAccountManagementServlet
            case "editAccountInformation":
                entityManager.getTransaction().begin();
                UserAccount userAccount = entityManager.find(UserAccount.class, req.getSession().getAttribute("authenticatedUser"));
                userAccount.getPerson().setName(req.getParameter("newName"));
                userAccount.getPerson().getAddress().setAddress(req.getParameter("newAddress"));
                userAccount.getPerson().getContactDetails().setPhoneNumber(req.getParameter("newPhoneNumber"));
                userAccount.getPerson().getContactDetails().setEmail(req.getParameter("newEmail"));
                entityManager.merge(userAccount);
                entityManager.flush();
                entityManager.getTransaction().commit();
                setReqModalMessage(req, new ModalMessage(GREEN, "Personal details updated successfully!", null));
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;}}
}
