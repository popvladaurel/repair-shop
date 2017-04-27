package ro.vlad.persitence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public final class PersistenceFactory {

    private static final String PERSISTENCE_UNIT_NAME = "repairshop";

    private final static class PersistenceFactoryHolder {
        private final static EntityManagerFactory instance = createPersistenceFactory();

        private static EntityManagerFactory createPersistenceFactory() {
            try {
                return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (Throwable t) {
                return null;
            }
        }
    }

    private PersistenceFactory() {
    }

    public static final EntityManagerFactory getInstance() {
        return PersistenceFactoryHolder.instance;
    }

}
