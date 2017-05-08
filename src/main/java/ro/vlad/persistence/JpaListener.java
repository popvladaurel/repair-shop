package ro.vlad.persistence;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class JpaListener implements ServletContextListener {
    public static final String PERSISTENCE_FACTORY = "PERSISTENCE_FACTORY";
    public static final Logger LOGGER = LoggerFactory.getLogger("RepairShop");

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = PersistenceFactory.getInstance();
        servletContextEvent.getServletContext().setAttribute(PERSISTENCE_FACTORY, factory);
        LOGGER.info("Init of persistence factory successful");}

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = (EntityManagerFactory) servletContextEvent.getServletContext().getAttribute(PERSISTENCE_FACTORY);
        factory.close();
        LOGGER.info("Closing of the persistence factory successful");}
}
