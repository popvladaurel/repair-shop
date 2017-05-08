package ro.vlad.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceFactory {
    private static final String PERSISTENCE_UNIT_NAME = "RepairShop";
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceFactory.class.getName());

    public static final EntityManagerFactory getInstance() {return PersistenceFactoryHolder.instance;}

    private final static class PersistenceFactoryHolder {
        private final static EntityManagerFactory instance = createPersistenceFactory();
        private static EntityManagerFactory createPersistenceFactory() {
            try{
                LOGGER.info("Setup of persistence unit complete!");
                return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);}
            catch(Throwable t){
                LOGGER.error("Failed to setup persistence unit!", t);
                return null;}}}
}