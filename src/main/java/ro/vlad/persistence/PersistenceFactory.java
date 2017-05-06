package ro.vlad.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.vlad.security.LoginServlet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceFactory {
    private static final String PERSISTENCE_UNIT_NAME = "RepairShop";
    private static final Logger LOGGER = LoggerFactory.getLogger("RepairShop");

    public static final EntityManagerFactory getInstance() {return PersistenceFactoryHolder.instance;}

    private final static class PersistenceFactoryHolder {
        private final static EntityManagerFactory instance = createPersistenceFactory();
        private static EntityManagerFactory createPersistenceFactory() {
            try{
                return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);}
            catch(Throwable t){return null;}}}
}