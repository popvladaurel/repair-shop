package ro.vlad.security;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ro.vlad.persistence.JpaListener.LOGGER;

/**
 * Handles all the login requests and check the session for existence of the "authenticatedUser" attribute
 * It defaults to the home page and invalidates the session if requirements are not met
 */
@WebFilter(urlPatterns = "/jsp/*", filterName = "Gandalf In Moria")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("authenticatedUser") != null;
        boolean loginRequest = request.getRequestURI().equals("WEB-INF/jsp/home.jsp");
        if (loggedIn || loginRequest) {
            LOGGER.info("Authenticated! You may pass...");
            chain.doFilter(request, response);}
        else {
            LOGGER.error("Not authenticated! You shall not pass! Redirecting to home.jsp...");
            response.sendRedirect("WEB-INF/jsp/home.jsp");}}

    public void destroy() {}
}
