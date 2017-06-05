package ro.vlad.security;

import ro.vlad.utils.Modal;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.utils.Colors.GREEN;
import static ro.vlad.utils.Colors.RED;
import static ro.vlad.utils.Modal.setMessage;

/**
 * Handles all the login requests and check the session for existence of the "authenticatedUser" attribute
 * It defaults to the home page and invalidates the session if requirements are not met
 */
@WebFilter(urlPatterns = "/login.jsp", filterName = "Gandalf In Moria")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        boolean validCookiePresent = false;
        Cookie cookies[] = request.getCookies();
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authenticatedUser") && (cookie.getMaxAge() != 0)) {
                    cookie.setMaxAge(30 * 60);
                    response.addCookie(cookie);
                    validCookiePresent = true;
                    session.setAttribute("authenticatedUser", cookie.getValue());
                    LOGGER.info("Valid cookie present.");}}}
        boolean loggedIn = validCookiePresent;
        if (loggedIn) {
            LOGGER.info("Authenticated! You may pass...");
            setMessage(request, new Modal(GREEN, "Welcome back, " + session.getAttribute("authenticatedUser").toString(),  null));
            session.getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);}
        else {
            LOGGER.error("Not authenticated! You shall not pass! Redirecting to login page...");
            setMessage(request, new Modal(RED, "Session expired, please re-login!",  null));
            session.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);}}

    public void destroy() {}
}
