package ro.vlad.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;

@WebServlet(urlPatterns = "/userAccountManagementServlet")
public class UserAccountManagementServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory)getServletContext().getAttribute(PERSISTENCE_FACTORY);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserAccountAction.add(entityManager, req.getParameter("newaccountname"), req.getParameter("newname"), req.getParameter("newemail"), req.getParameter("newpassword"));
        System.out.println("New user " +  req.getParameter("newaccountname") + " added!");
        resp.sendRedirect("jsp/welcome.jsp");}
}
