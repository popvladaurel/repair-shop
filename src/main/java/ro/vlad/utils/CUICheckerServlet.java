package ro.vlad.utils;

import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ro.vlad.utils.ModalMessage.Color.GREEN;
import static ro.vlad.utils.ModalMessage.Color.RED;
import static ro.vlad.utils.ModalMessage.setReqModalMessage;

@WebServlet(urlPatterns = "/CUICheckerServlet", name = "CUICheckerServlet")
public class CUICheckerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject openAPIcompanyJSON = CUICheckerOpenAPI.checkCUI(req.getParameter("companyCUI"));
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
            setReqModalMessage(req, new ModalMessage(GREEN, "Data acquired from ANAF!", "/jsp/company.jsp"));}
        else {
            setReqModalMessage(req, new ModalMessage(RED, "No API available", "/jsp/company.jsp"));}
        req.setAttribute("newCompanyState", openAPIcompanyJSON.get("mesaj").toString());
        req.setAttribute("newAnafMessage", anafAPIcompanyJSON.get("mesaj").toString());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);}
}
//TODO implement method to change expired OpenAPI key and link it to the corresponding button in the side bar Settings panel

