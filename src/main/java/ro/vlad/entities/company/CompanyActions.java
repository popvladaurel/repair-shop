package ro.vlad.entities.company;

import javax.persistence.EntityManager;
import java.util.List;

class CompanyActions {
    private EntityManager entityManager;

    CompanyActions(EntityManager entityManager){this.entityManager = entityManager;}

    void addCompany(Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.flush();
        entityManager.getTransaction().commit();}

    List<Company> listCompanies() {
        List<Company> companiesList = entityManager.createQuery("FROM ro.vlad.entities.company.Company company").getResultList();
        return companiesList;}

    void deleteCompany(Company company) {
        entityManager.getTransaction().begin();
        entityManager.remove(company);
        entityManager.flush();
        entityManager.getTransaction().commit();}

    Company getCompanyByCUI (String CUI) {
        return entityManager.find(Company.class, CUI);}
}
