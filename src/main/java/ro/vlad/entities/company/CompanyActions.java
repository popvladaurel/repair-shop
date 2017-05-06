package ro.vlad.entities.company;

import ro.vlad.entities.address.Address;
import ro.vlad.entities.contactDetails.ContactDetails;
import ro.vlad.entities.person.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class CompanyActions {
    private EntityManager entityManager;
    public CompanyActions (EntityManager entityManager){this.entityManager = entityManager;}

    public void addCompany(Company newCompany) {
        entityManager.getTransaction().begin();
        entityManager.persist(newCompany);
        entityManager.flush();
        entityManager.getTransaction().commit();}

    public List<Company> listCompanies() {
        List<Company> companiesList = entityManager.createQuery("FROM ro.vlad.entities.company.Company company").getResultList();
        return companiesList;}

    public void deleteCompany(Company company) {
        entityManager.getTransaction().begin();
        entityManager.remove(company);
        entityManager.flush();
        entityManager.getTransaction().commit();}

    public Company getCompanyByCUI (String CUI) {
        return entityManager.find(Company.class, CUI);}
}
