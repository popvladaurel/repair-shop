package ro.vlad.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import ro.vlad.entities.userAccount.UserAccount;
import ro.vlad.entities.userAccount.UserAccountActions;
import ro.vlad.utils.ModalMessage;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;
import static ro.vlad.utils.ModalMessage.Color.GREEN;
import static ro.vlad.utils.ModalMessage.Color.RED;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

/**
 * Processes the login request, compares it's parameters against the contents of the database and decides where to send the user
 * Displays a relevant message, depending on the circumstances
 */
@WebServlet(urlPatterns = "/loginServlet", name = "What's the Word?!")
public class LoginServlet extends HttpServlet {
    private EntityManager entityManager;
    private UserAccountActions userAccountActions;

    @Override
    public void init() throws ServletException {
        super.init();}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory) getServletContext().getAttribute(PERSISTENCE_FACTORY);
        entityManager = entityManagerFactory.createEntityManager();
        userAccountActions = new UserAccountActions(entityManager);
        String loginUserName = req.getParameter("loginUserName");
        String loginPassword = req.getParameter("loginPassword");
        if (userAccountActions.getUserAccountByAccountName(loginUserName) == null) {
            LOGGER.warn("Trying to login with non-existing account...");
            setReqModalMessage(req, new ModalMessage(RED, "This account does not exist.", null));}
            else if (userAccountActions.userAccountWasDeleted(loginUserName)) {
                LOGGER.warn("Trying to login with deleted account...");
                setReqModalMessage(req, new ModalMessage(RED, "This account was deleted.", null));}
                else {
                    String query = "FROM ro.vlad.entities.userAccount.UserAccount storedUserAccount " + "WHERE storedUserAccount.accountId=" + "\'" +  loginUserName + "\'";
                    UserAccount storedUserAccount = (UserAccount) entityManager.createQuery(query).getSingleResult();
                    try {
                        if (!PasswordStorage.verifyPassword(loginPassword, storedUserAccount.getPasswordHash())) {
                            LOGGER.warn("Trying to login with wrong password account...");
                            setReqModalMessage(req, new ModalMessage(RED, "Wrong password. Try again.", null));}
                            else {
                                req.getSession().setAttribute("authenticatedUser", loginUserName);
                                LOGGER.info("Login successfull!");
//TODO display User name in the nav bar after login
                                setReqModalMessage(req, new ModalMessage(GREEN , "Welcome!", null));}}
                    catch (PasswordStorage.CannotPerformOperationException e) {
                        e.printStackTrace();
                        LOGGER.error("Something went wrong while verifying password..." + e.toString());}
                    catch (PasswordStorage.InvalidHashException e) {
                        e.printStackTrace();
                        LOGGER.error("Something went wrong with the PasswordHash generator..." + e.toString());}}
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
}