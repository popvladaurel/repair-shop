package ro.vlad.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import ro.vlad.entities.userAccount.UserAccount;
import ro.vlad.entities.userAccount.UserAccountActions;
import ro.vlad.utils.Modal;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;
import static ro.vlad.utils.Colors.*;
import static ro.vlad.utils.Modal.setMessage;

/**
 * Processes the login request, compares it's parameters against the contents of the database and decides where to send the user
 * Displays a relevant message, depending on the circumstances
 */
@WebServlet(urlPatterns = "/loginServlet", name = "What's the Word?!")
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory) getServletContext().getAttribute(PERSISTENCE_FACTORY);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserAccountActions userAccountActions = new UserAccountActions(entityManager);
        String loginUserName = req.getParameter("loginUserName");
        String loginPassword = req.getParameter("loginPassword");
        if (userAccountActions.userAccountWasDeleted(loginUserName)) {
            LOGGER.warn("Trying to login with deleted account...");
            setMessage(req, new Modal(RED, "This account was deleted.", null));
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);}
        else if (userAccountActions.getUserAccountByAccountName(loginUserName) == null) {
            LOGGER.warn("Trying to login with non-existing account...");
            setMessage(req, new Modal(RED, "This account does not exist.", null));
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);}
        else {
            String query = "FROM ro.vlad.entities.userAccount.UserAccount storedUserAccount " + "WHERE storedUserAccount.accountId=" + "\'" +  loginUserName + "\'";
            UserAccount storedUserAccount = (UserAccount) entityManager.createQuery(query).getSingleResult();
            try {
                if (!PasswordStorage.verifyPassword(loginPassword, storedUserAccount.getPasswordHash())) {
                    LOGGER.warn("Trying to login with wrong password account...");
                    setMessage(req, new Modal(RED, "Wrong password. Try again.", null));
                    getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);}
                else {
                    req.getSession().setAttribute("authenticatedUser", loginUserName);
                    Cookie cookie = new Cookie("authenticatedUser", loginUserName);
                    cookie.setMaxAge(30 * 60);
                    resp.addCookie(cookie);
                    LOGGER.info("Login successful!");
                    setMessage(req, new Modal(GREEN , "Welcome!", null));
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);}}
            catch (PasswordStorage.CannotPerformOperationException e) {
                e.printStackTrace();
                LOGGER.error("Something went wrong while verifying password..." + e.toString());
                setMessage(req, new Modal(GREEN , "Something went wrong while verifying password..." + e.toString(), null));
                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);}
            catch (PasswordStorage.InvalidHashException e) {
                e.printStackTrace();
                LOGGER.error("Something went wrong with the PasswordHash generator..." + e.toString());
                getServletContext().getRequestDispatcher("Something went wrong with the PasswordHash generator..." + e.toString()).forward(req, resp);}}}
}