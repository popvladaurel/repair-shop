package ro.vlad.utils;

import org.json.JSONObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.utils.CUICheckerOpenAPI.getKey;
import static ro.vlad.utils.CUICheckerOpenAPI.setKey;
import static ro.vlad.utils.Colors.*;
import static ro.vlad.utils.Modal.setMessage;

/**
 * The controller for CUICheckerAnafAPI and CUICheckerOpenAPI classes
 * Analyses data received from those two classes and decides what to transmit to the page
 * Triggers relevant messages for every possible situation
 */
@WebServlet(urlPatterns = "/CUICheckerServlet", name = "Could this be a scam??")
public class CUICheckerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject openAPIcompanyJSON = CUICheckerOpenAPI.checkCUI(req.getParameter("newCompanyCUI"));
        JSONObject anafAPIcompanyJSON = CUICheckerAnafAPI.checkCUI(req.getParameter("newCompanyCUI"));
        if ((Boolean) openAPIcompanyJSON.get("valid")) {
            req.setAttribute("newCompanyCUI", openAPIcompanyJSON.get("cif").toString());
            req.setAttribute("newJ", openAPIcompanyJSON.get("numar_reg_com").toString());
            req.setAttribute("newCompanyName", openAPIcompanyJSON.get("denumire").toString());
            req.setAttribute("newCompanyAddress", openAPIcompanyJSON.get("adresa").toString() + ", " + openAPIcompanyJSON.get("judet"));
            req.setAttribute("newCompanyPhone", openAPIcompanyJSON.get("telefon").toString());
            if ((Boolean) anafAPIcompanyJSON.get("valid")) {
                req.setAttribute("radiata", (anafAPIcompanyJSON.get("mesaj").equals("nu figureaza in registre ")));
                setMessage(req, new Modal(GREEN, "Data acquired from ANAF and OpenAPI!", "/WEB-INF/jsp/company/company.jsp"));}
            else {
                setMessage(req, new Modal(GREEN, "Data acquired from OpenAPI!", "/WEB-INF/jsp/company/company.jsp"));}}
        else if ((Boolean) anafAPIcompanyJSON.get("valid")) {
            req.setAttribute("newCompanyCUI", anafAPIcompanyJSON.get("cui").toString());
            req.setAttribute("newCompanyName", anafAPIcompanyJSON.get("denumire").toString());
            req.setAttribute("newCompanyAddress", anafAPIcompanyJSON.get("adresa").toString());
            req.setAttribute("radiata", (anafAPIcompanyJSON.get("mesaj").equals("nu figureaza in registre ")));
            setMessage(req, new Modal(GREEN, "Data acquired from ANAF!", "/WEB-INF/jsp/company/company.jsp"));}
        else {
            setMessage(req, new Modal(RED, "No API available. Start typing...", "/WEB-INF/jsp/company/company.jsp"));}
        req.setAttribute("newCompanyState", openAPIcompanyJSON.get("mesaj").toString());
        req.setAttribute("newAnafMessage", anafAPIcompanyJSON.get("mesaj").toString());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        dispatcher.forward(req, resp);}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        LOGGER.info("GET command received: " + action);
        switch (action) {
            case "changeKey":
                String newOpenAPIKey = req.getParameter("openApiKey");
                LOGGER.info("Changing OpenAPI key...");
                setKey(newOpenAPIKey);
                setMessage(req, new Modal(GREEN, "OpenAPI key changed. Try adding a company", "/WEB-INF/jsp/company/company.jsp"));
                break;
            case "viewKey":
                LOGGER.info("Getting OpenAPI key...");
                setMessage(req, new Modal(BLUE, getKey(), null));
                break;}
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        dispatcher.forward(req, resp);}
}

