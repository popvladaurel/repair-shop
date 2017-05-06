package ro.vlad.entities.person;

import javax.persistence.EntityManager;

public class PersonActions {
    private EntityManager entityManager;

    PersonActions(EntityManager entityManager){this.entityManager = entityManager;}

    public void addPerson(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}
