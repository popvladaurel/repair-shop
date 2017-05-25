package ro.vlad.security;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ro.vlad.utils.Modal.Color.GREEN;
import static ro.vlad.utils.Modal.Color.RED;
import static ro.vlad.utils.Modal.setMessage;

@WebServlet(urlPatterns = "/oauth2callback")
public class OAuth2Callback extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      JSONObject googleJson = new JSONObject();
      try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
          HttpPost request = new HttpPost("https://accounts.google.com/o/oauth2/token");
          List<NameValuePair> urlParameters = new ArrayList<>(1);
          urlParameters.add(new BasicNameValuePair("code", req.getParameter("code")));
          urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
          urlParameters.add(new BasicNameValuePair("client_id", Setup.CLIENT_ID));
          urlParameters.add(new BasicNameValuePair("client_secret", Setup.CLIENT_SECRET));
          urlParameters.add(new BasicNameValuePair("redirect_uri", Setup.REDIRECT_URL));
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
          String accessToken = googleJson.getString("access_token");
          request = new HttpPost("https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + accessToken);
          response = httpClient.execute(request);
          entity = response.getEntity();
          googleJson = new JSONObject(EntityUtils.toString(entity));
          req.getSession().setAttribute("authenticatedUser", googleJson.getString("name"));
          req.setAttribute("userName", googleJson.getString("name"));
          req.setAttribute("userImage", googleJson.getString("picture"));
          setMessage(req, new Modal(GREEN, "Welcome " + googleJson.getString("name"), null));}
      catch (IOException e) {
          req.getSession().setAttribute("authenticatedUser", null);
          setMessage(req, new Modal(RED, "Could not login using Google!", null));}
      System.out.println(googleJson.toString());
      getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);}
}
