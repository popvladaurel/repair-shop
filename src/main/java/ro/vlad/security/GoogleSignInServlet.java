package ro.vlad.security;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import ro.vlad.utils.Modal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.utils.Colors.*;
import static ro.vlad.utils.Modal.setMessage;

@WebServlet(urlPatterns = "/googleSignInServlet", name = "Soo... is Google still evil?")
public class GoogleSignInServlet extends HttpServlet {
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    private static String REDIRECT_URL;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "login" : req.getParameter("action");
        LOGGER.info("GET command received: " + action);
        switch (action) {
            case "setupParameters":
                String newCLIENT_ID = req.getParameter("CLIENT_ID");
                String newCLIENT_SECRET = req.getParameter("CLIENT_SECRET");
                String newREDIRECT_URL = req.getParameter("REDIRECT_URL");
                LOGGER.info("Setting Google parameters...");
                setGoogleParameters(newCLIENT_ID, newCLIENT_SECRET, newREDIRECT_URL);
                setMessage(req, new Modal(GREEN, "Google setup complete!", null));
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
                break;
            case "login":
                JSONObject googleJson;
                try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
                    HttpPost request = new HttpPost("https://accounts.google.com/o/oauth2/token");
                    List<NameValuePair> urlParameters = new ArrayList<>(1);
                    urlParameters.add(new BasicNameValuePair("code", req.getParameter("code")));
                    urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
                    urlParameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
                    urlParameters.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
                    urlParameters.add(new BasicNameValuePair("redirect_uri", REDIRECT_URL));
                    request.setHeader("Host", "accounts.google.com");
                    request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                    request.setHeader("Accept-Language", "en-US,en;q=0.5");
                    request.setHeader("Connection", "keep-alive");
                    request.setHeader("Referrer", "https://accounts.google.com/o/oauth2/token");
                    request.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    request.setEntity(new UrlEncodedFormEntity(urlParameters));
                    HttpResponse response = httpClient.execute(request);
                    HttpEntity entity = response.getEntity();
                    googleJson = new JSONObject(EntityUtils.toString(entity));
                    System.out.println(googleJson.toString());
                    String accessToken = googleJson.getString("access_token");
                    request = new HttpPost("https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + accessToken);
                    response = httpClient.execute(request);
                    entity = response.getEntity();
                    googleJson = new JSONObject(EntityUtils.toString(entity));
                    System.out.println(googleJson.toString());
                    Cookie cookie = new Cookie("authenticatedUser", googleJson.getString("sub"));
                    cookie.setMaxAge(30 * 60);
                    resp.addCookie(cookie);
                    req.getSession().setAttribute("authenticatedUser", googleJson.getString("name"));
                    req.setAttribute("userName", googleJson.getString("name"));
                    req.setAttribute("userImage", googleJson.getString("picture"));
                    setMessage(req, new Modal(GREEN, "Welcome " + googleJson.getString("name"), null));
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);}
                catch (IOException e) {
                    req.getSession().setAttribute("authenticatedUser", null);
                    setMessage(req, new Modal(RED, "Could not login using Google!", null));
                    getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);}
                break;}}

    private void setGoogleParameters(String newCLIENT_ID, String newCLIENT_SECRET, String newREDIRECT_URL) {
        CLIENT_ID = newCLIENT_ID;
        CLIENT_SECRET = newCLIENT_SECRET;
        REDIRECT_URL = newREDIRECT_URL;}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((CLIENT_ID == null) || (CLIENT_SECRET == null) || (REDIRECT_URL == null)) {
            setMessage(req, new Modal(RED, "Google parameters not set!", null));
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);}
        else {
            List<NameValuePair> urlParameters = new ArrayList<>(1);
            urlParameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
            urlParameters.add(new BasicNameValuePair("response_type", "code"));
            urlParameters.add(new BasicNameValuePair("scope", "email"));
            urlParameters.add(new BasicNameValuePair("redirect_uri", REDIRECT_URL));
            urlParameters.add(new BasicNameValuePair("approval_prompt", "force"));
            String url = "https://accounts.google.com/o/oauth2/auth" + "?" + URLEncodedUtils.format(urlParameters, "utf-8");
            resp.sendRedirect(url);}}
}

