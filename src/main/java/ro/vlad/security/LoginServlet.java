package ro.vlad.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import ro.vlad.entities.userAccount.UserAccount;
import ro.vlad.entities.userAccount.UserAccountActions;

import static ro.vlad.persistence.JpaListener.PERSISTENCE_FACTORY;

@WebServlet(urlPatterns = "/loginServlet", name = "loginServlet")
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
            req.setAttribute("modalMessage", "This account does not exist.");
            req.setAttribute("modalShow", "block");
            req.setAttribute("pageToShowInTheMainBody", null);}
            else if (userAccountActions.userAccountWasDeleted(loginUserName)) {
                req.setAttribute("modalMessage", "This account was deleted.");
                req.setAttribute("modalShow", "block");
                req.setAttribute("pageToShowInTheMainBody", null);}
                else {
                    String query = "FROM ro.vlad.entities.userAccount.UserAccount storedUserAccount " +
                                    "WHERE storedUserAccount.accountId=" + "\'" +  loginUserName + "\'";
                    UserAccount storedUserAccount = (UserAccount) entityManager.createQuery(query).getSingleResult();
                    try {
                        if (!PasswordStorage.verifyPassword(loginPassword, storedUserAccount.getPasswordHash())) {
                            req.setAttribute("modalMessage", "Wrong password. Try again.");
                            req.setAttribute("modalShow", "block");
                            req.setAttribute("pageToShowInTheMainBody", null);}
                            else {
                                req.getSession().setAttribute("authenticatedUser", loginUserName);
                                try {
                                    req.setAttribute("username", storedUserAccount.getPerson().getName());
                                    req.setAttribute("pageToShowInTheMainBody", null);}
                                catch ( NullPointerException e) {req.setAttribute("username", "User");}}}
                    catch (PasswordStorage.CannotPerformOperationException e) {e.printStackTrace();}
                    catch (PasswordStorage.InvalidHashException e) {e.printStackTrace();}}
        req.setAttribute("pagetoshowinthemainbody", null);
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
}