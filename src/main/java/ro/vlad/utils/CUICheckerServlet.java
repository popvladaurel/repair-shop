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
import static ro.vlad.utils.ModalMessage.Color.BLUE;
import static ro.vlad.utils.ModalMessage.Color.GREEN;
import static ro.vlad.utils.ModalMessage.Color.RED;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

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
                setReqModalMessage(req, new ModalMessage(GREEN, "Data acquired from ANAF and OpenAPI!", "/jsp/company.jsp"));}
            else {
                setReqModalMessage(req, new ModalMessage(GREEN, "Data acquired from OpenAPI!", "/jsp/company.jsp"));}}
        else if ((Boolean) anafAPIcompanyJSON.get("valid")) {
            req.setAttribute("newCompanyCUI", anafAPIcompanyJSON.get("cui").toString());
            req.setAttribute("newCompanyName", anafAPIcompanyJSON.get("denumire").toString());
            req.setAttribute("newCompanyAddress", anafAPIcompanyJSON.get("adresa").toString());
            req.setAttribute("radiata", (anafAPIcompanyJSON.get("mesaj").equals("nu figureaza in registre ")));
            setReqModalMessage(req, new ModalMessage(GREEN, "Data acquired from ANAF!", "/jsp/company.jsp"));}
        else {
            setReqModalMessage(req, new ModalMessage(RED, "No API available. Start typing...", "/jsp/company.jsp"));}
        req.setAttribute("newCompanyState", openAPIcompanyJSON.get("mesaj").toString());
        req.setAttribute("newAnafMessage", anafAPIcompanyJSON.get("mesaj").toString());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
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
                setReqModalMessage(req, new ModalMessage(GREEN, "OpenAPI key changed. Try adding a company", "/jsp/company.jsp"));
            case "viewKey":
                LOGGER.info("Getting OpenAPI key...");
                setReqModalMessage(req, new ModalMessage(BLUE, getKey(), null));}
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);}
}
//TODO implement method to change expired OpenAPI key and link it to the corresponding button in the side bar Settings panel

