package ro.vlad.persitence;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JpaListener implements ServletContextListener {

    public static final String PERSISTENCE_FACTORY = "PERSISTENCE_FACTORY";

    public void contextInitialized(ServletContextEvent sce) {
        // make the factory lazy init at startup so that the first call doesn't yield in an error
        EntityManagerFactory factory = PersistenceFactory.getInstance();
        sce.getServletContext().setAttribute(PERSISTENCE_FACTORY, factory);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory factory = (EntityManagerFactory) sce.getServletContext().getAttribute(PERSISTENCE_FACTORY);
        factory.close();
    }


}
