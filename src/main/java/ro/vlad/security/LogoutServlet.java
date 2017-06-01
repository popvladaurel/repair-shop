package ro.vlad.security;

import ro.vlad.utils.Modal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.utils.Colors.*;
import static ro.vlad.utils.Modal.setMessage;

/**
 * Called when a user decides to close the session
 * Nullifies the "authenticatedUser" parameter of the session and invalidates it
 */
@WebServlet(urlPatterns = "/logoutServlet", name = "May we meet again...")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Logout detected. Invalidating session...");
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            LOGGER.info("Session invalidated!");}
        req.getSession();
        setMessage(req, new Modal(GREEN, "Have a nice day!",  null));
        resp.sendRedirect("home.jsp");}
}