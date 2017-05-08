package ro.vlad.security;

import ro.vlad.utils.ModalMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.utils.ModalMessage.Color.GREEN;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

@WebServlet(urlPatterns = "/logoutServlet", name = "May we meet again...")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Logout detected. Invalidating session...");
        HttpSession session = req.getSession(false);
        session.setAttribute("authenticatedUser", null);
        session.invalidate();
        setReqModalMessage(req, new ModalMessage(GREEN, "Have a nice day!",  null));
        LOGGER.info("Session invalidated!");
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
}