package ro.vlad.servlets;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ro.vlad.persitence.JpaListener.PERSISTENCE_FACTORY;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute(PERSISTENCE_FACTORY);
        EntityManager em = emf.createEntityManager();
        Query querry = em.createQuery("SELECT * FROM UserData WHERE UserName='" + req.getParameter("name") + "'");
        System.out.println(querry);

    }
}
