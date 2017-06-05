package ro.vlad.security;

import ro.vlad.utils.Modal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        boolean cookieInvalidated = false;
        Cookie cookies[] = req.getCookies();
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authenticatedUser")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    cookieInvalidated = true;
                    LOGGER.info("Cookies nullified!");}}}
        HttpSession session = req.getSession(false);
        if ((session != null) && cookieInvalidated) {
            session.invalidate();
            LOGGER.info("Session invalidated!");}
        req.getSession();
        setMessage(req, new Modal(GREEN, "Have a nice day!",  null));
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);}
}