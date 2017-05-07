package ro.vlad.entities.company;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
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
import static ro.vlad.utils.ModalMessage.Color.GREEN;
import static ro.vlad.utils.ModalMessage.Color.RED;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

@WebServlet(urlPatterns = "/companyManagementServlet", name = "companyManagementServlet")
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "listCompanies";
        switch (action) {
//TODO Move addCompany1 to doGET and fix getting company INFO
            case "addCompany1":
                req.setAttribute("pathToServlet", "/companyManagementServlet?action=addCompany2");
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("pageToShowInTheMainBody", "/jsp/company.jsp");
//TODO Add a blue instructions message "Input Company cui and click on Verify Company"
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;
            case "addCompany2":
                String newCompanyCUI = req.getParameter("newCompanyCUI");
                if (companyActions.getCompanyByCUI(newCompanyCUI) != null) {
                    setReqModalMessage(req, new ModalMessage(RED, "This company is already in the database!", "/jsp/company.jsp"));}
                else {
                    String newJ = req.getParameter("newJ");
                    String newCompanyName = req.getParameter("newCompanyName");
                    String newCompanyIBAN = req.getParameter("newCompanyIBAN");
                    Company newCompany = new Company(newCompanyCUI, newJ, newCompanyName, newCompanyIBAN);
                    ContactDetails newContactDetails = new ContactDetails(req.getParameter("newCompanyPhone"), req.getParameter("newCompanyEmail"));
                    Address newAddress = new Address(req.getParameter("newCompanyAddress"));
                    newCompany.setContactDetails(newContactDetails);
                    newCompany.setAddress(newAddress);
                    companyActions.addCompany(newCompany);
                    setReqModalMessage(req, new ModalMessage(GREEN, newCompanyName + " added!", null));}
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmButton", "Add Company");
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;}}
}
//TODO Implement doGet and edit and list methods

