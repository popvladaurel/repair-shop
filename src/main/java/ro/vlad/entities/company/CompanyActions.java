package ro.vlad.entities.company;

import javax.persistence.EntityManager;
import java.util.List;

import static ro.vlad.persistence.JpaListener.LOGGER;

/**
 * Basic Company class actions
 */
class CompanyActions {
    private EntityManager entityManager;

    CompanyActions(EntityManager entityManager){this.entityManager = entityManager;}

    /**
     * Adding a new Company entity to the database
     * @param company passed from the CompanyManagementServlet
     */
    void addCompany(Company company) {
        LOGGER.info("Adding new company...");
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.flush();
        entityManager.getTransaction().commit();
        LOGGER.info("New company added!");}

    /**
     * Lists all the entries in the companies table
     * @return List of Company entities or null
     */
    List<Company> listCompanies() {
        LOGGER.info("Fetching companies list...");
        List<Company> companiesList = entityManager.createQuery("SELECT company FROM ro.vlad.entities.company.Company company").getResultList();
        if (companiesList != null) {
            LOGGER.info("Companies list retrieved successfully!");
            return companiesList;}
        else {
            LOGGER.info("No companies stored yet, nothing to show.");
            return null;}}

    void deleteCompany(Company company) {
        LOGGER.info("Removing company...");
        entityManager.getTransaction().begin();
        entityManager.remove(company);
        entityManager.flush();
        entityManager.getTransaction().commit();
        LOGGER.info("Company removed!");}

    /**
     * Grabs a Company by identifier
     * @param CUI table identifier for Company
     * @return a Company entity associated with the supplied CUI
     */
    Company getCompanyByCUI (String CUI) {
        return entityManager.find(Company.class, CUI);}
}
