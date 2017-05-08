package ro.vlad.security;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ro.vlad.persistence.JpaListener.LOGGER;

@WebFilter(urlPatterns = "/jsp/*", filterName = "Gandalf In Moria")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("authenticatedUser") != null;
        boolean loginRequest = request.getRequestURI().equals("home.jsp");
        if (loggedIn || loginRequest) {
            LOGGER.info("Authenticated! You may pass...");
            chain.doFilter(request, response);}
            else {
            LOGGER.info("Not authenticated! You shall not pass! Redirecting to home.jsp...");
            response.sendRedirect("../home.jsp");}}

    @Override
    public void destroy() {}
}
