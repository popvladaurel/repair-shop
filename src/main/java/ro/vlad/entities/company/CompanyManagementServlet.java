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

@WebServlet(urlPatterns = "/companyManagementServlet")
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
            case "addCompany":
                Company newCompany = new Company();
                Address newAddress = new Address();
                ContactDetails newContactDetails = new ContactDetails();
                newCompany.setCUI(req.getParameter("newcompanycui"));
                newCompany.setJ(req.getParameter("newj"));
                newCompany.setName(req.getParameter("newcompanyname"));
                newCompany.setIBAN( req.getParameter("newcompanyiban"));
                newAddress.setAddress(req.getParameter("newcompanyaddress"));
                newContactDetails.setPhoneNumber(req.getParameter("newcompanyphone"));
                newContactDetails.setEmail(req.getParameter("newcompanyemail"));
                newCompany.setAddress(newAddress);
                newCompany.setContactDetails(newContactDetails);
                companyActions.addCompany(newCompany);
                resp.sendRedirect("../jsp/addCustomer.jsp");
                break;}}
}

