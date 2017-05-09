package ro.vlad.entities.person;

import javax.persistence.EntityManager;

/**
 * Basic actions for the Person class
 */
public class PersonActions {
    private EntityManager entityManager;

    PersonActions(EntityManager entityManager){this.entityManager = entityManager;}

    /**
     * Adds a Person entity to the database
     * @param person received from the PersonManagementServlet
     */
    public void addPerson(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.flush();
        entityManager.getTransaction().commit();}

//TODO implement the rest of the actions
}
