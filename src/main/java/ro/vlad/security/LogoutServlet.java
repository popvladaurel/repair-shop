package ro.vlad.security;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logoutServlet", name = "logoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.setAttribute("authenticatedUser", null);
        session.invalidate();
        req.setAttribute("modalMessage", "Have a nice day!.");
        req.setAttribute("modalShow", "block");
        req.setAttribute("pageToShowInTheMainBody", null);
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}


    public static void clearSession (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        session.setAttribute("authenticatedUser", null);
        session.invalidate();
        resp.sendRedirect("home.jsp");}
}