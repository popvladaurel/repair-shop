package ro.vlad.entities.company;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.userAccount.UserAccountActions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;

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
            case "addCompany1":
                req.setAttribute("pathToServlet", "${pageContext.request.contextPath}/userAccountManagementServlet?action=addCompany2");
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmButton", "Add User");
                req.setAttribute("pageToShowInTheMainBody", "/jsp/addCompany.jsp");
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;
            case "addCompany2":
                String newCompanyName = "";
                String newCompanyCUI = req.getParameter("newCompanyCUI");
                if (companyActions.getCompanyByCUI(newCompanyCUI) == null) {
                    req.setAttribute("modalMessage", "This company is already in the database!");
                    //add here a redirect to the editCompany page after a "Would you like to edit instead of add?"
                    req.setAttribute("pageToShowInTheMainBody", "/home.jsp");}
                else {
                    String newJ = req.getParameter("newJ");
                    newCompanyName = req.getParameter("newCompanyName");
                    String newCompanyIBAN = req.getParameter("newCompanyIBAN");
                    Company newCompany = new Company(newCompanyCUI, newJ, newCompanyName, newCompanyIBAN);
                    ContactDetails newContactDetails = new ContactDetails(req.getParameter("newCompanyPhone"), req.getParameter("newCompanyEmail"));
                    Address newAddress = new Address(req.getParameter("newCompanyAddress"));
                    newCompany.setContactDetails(newContactDetails);
                    newCompany.setAddress(newAddress);
                    companyActions.addCompany(newCompany);}
                req.setAttribute("modalMessage", newCompanyName + "added!");
                req.setAttribute("modalShow", "block");
                req.setAttribute("pageToShowInTheMainBody", null);
                req.setAttribute("show", "block");
                req.setAttribute("disabled", "");
                req.setAttribute("confirmButton", "Add User");
                req.setAttribute("pageToShowInTheMainBody", "/jsp/userAccount.jsp");
                getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
                break;}}}

