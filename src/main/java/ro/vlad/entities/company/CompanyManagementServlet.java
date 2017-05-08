package ro.vlad.entities.company;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.utils.ModalMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;
import static ro.vlad.utils.ModalMessage.Color.BLUE;
import static ro.vlad.utils.ModalMessage.Color.GREEN;
import static ro.vlad.utils.ModalMessage.Color.RED;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

@WebServlet(urlPatterns = "/companyManagementServlet", name = "Manage Companies")
public class CompanyManagementServlet extends HttpServlet {
    private EntityManager entityManager;
    private CompanyActions companyActions;

    @Override
    public void init() throws ServletException {
        super.init();
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory) getServletContext().getAttribute(PERSISTENCE_FACTORY);
        entityManager = entityManagerFactory.createEntityManager();
        companyActions = new CompanyActions(entityManager);}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        LOGGER.info("GET command received: " + action);
        action = (action != null) ? action : "listCompanies";
        switch (action) {
            case "addCompany":
                LOGGER.info("Adding company.jsp to home.jsp and enabling input fields...");
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmButton", "Add Company");
                setReqModalMessage(req, new ModalMessage(BLUE, "Input company CUI and click the Verify Company button.", "/jsp/company.jsp"));
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;}}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        LOGGER.info("POST command received: " + action);
        action = (action != null) ? action : "listCompanies";
        switch (action) {
            case "addCompany":
                String newCompanyCUI = req.getParameter("newCompanyCUI");
                if (newCompanyCUI == null || newCompanyCUI.equals("")) {
                    LOGGER.error("Tried to add a company without CUI.");
                    setReqModalMessage(req, new ModalMessage(RED, "Please check your data. Invalid or empty CUI field.", "/jsp/company.jsp"));}
                else if (companyActions.getCompanyByCUI(newCompanyCUI) != null) {
                        LOGGER.error("Tried to add an already existing company.");
                        setReqModalMessage(req, new ModalMessage(RED, "This company is already in the database!", "/jsp/company.jsp"));}
                    else {
                        LOGGER.info("Starting to add a new company...");
                        String newJ = req.getParameter("newJ");
                        String newCompanyName = req.getParameter("newCompanyName");
                        System.out.println(newCompanyName);
                        String newCompanyIBAN = req.getParameter("newCompanyIBAN");
                        Company newCompany = new Company(newCompanyCUI, newJ, newCompanyName, newCompanyIBAN);
                        ContactDetails newContactDetails = new ContactDetails(req.getParameter("newCompanyPhone"), req.getParameter("newCompanyEmail"));
                        Address newAddress = new Address(req.getParameter("newCompanyAddress"));
                        System.out.println(req.getParameter("newCompanyAddress"));
                        System.out.println(req.getParameter("newCompanyAddress"));
                        newCompany.setContactDetails(newContactDetails);
                        newCompany.setAddress(newAddress);
                        System.out.println(newCompany.getAddress().getAddress());
                        companyActions.addCompany(newCompany);
                        setReqModalMessage(req, new ModalMessage(GREEN, newCompanyName + " added!", null));
                        LOGGER.info("New company added!");}
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;}}
}
//TODO Implement edit and list methods

