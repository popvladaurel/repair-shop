package ro.vlad.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.Charset;

import static ro.vlad.persistence.JpaListener.LOGGER;

/**
 * Necessary for ensuring consistent character encoding between requests and responses
 * Forces UTF-8 on all traffic
 */
@WebFilter(urlPatterns = "*", filterName = "Encoding Sweeper")
public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";}}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");}
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //LOGGER.info("Character encoding used: " + Charset.defaultCharset().toString());
        chain.doFilter(req, resp);}

    public void destroy() {}
}
