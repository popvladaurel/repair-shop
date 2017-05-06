package ro.vlad.utils;

import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/CUICheckerServlet", name = "CUICheckerServlet")
public class CUICheckerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //JSONObject OpenAPIcompanyJSON = CUICheckerOpenAPI.checkCUI(req.getParameter("companyCUI"));
        JSONObject anafAPIcompanyJSON = CUICheckerAnafAPI.checkCUI(req.getParameter("newcompanycui"));
        if ((Boolean) anafAPIcompanyJSON.get("valid")) {
            req.setAttribute("newcompanycui", anafAPIcompanyJSON.get("cui").toString());
            req.setAttribute("newcompanyname", anafAPIcompanyJSON.get("denumire").toString());
            req.setAttribute("newcompanyaddress", anafAPIcompanyJSON.get("adresa").toString());
            req.setAttribute("anafmessage", anafAPIcompanyJSON.get("mesaj").toString());
            req.setAttribute("radiata",(anafAPIcompanyJSON.get("mesaj").equals("nu figureaza in registre ")));
            req.setAttribute("modalMessage", "Data acquired!");
            req.setAttribute("modalShow", "block");
            req.setAttribute("pageToShowInTheMainBody", null);}
            //req.setAttribute("newcompanycui", OpenAPIcompanyJSON.get("denumire").toString());
            //req.setAttribute("newj", OpenAPIcompanyJSON.get("numar_reg_com").toString());
            //req.setAttribute("newcompanyname", OpenAPIcompanyJSON.get("denumire").toString());
            //req.setAttribute("newcompanyaddress", OpenAPIcompanyJSON.get("adresa").toString() + ", " + OpenAPIcompanyJSON.get("judet"));
            //req.setAttribute("newcompanyphone", OpenAPIcompanyJSON.get("telefon").toString());
            //req.setAttribute("companystate", OpenAPIcompanyJSON.get("stare").toString());
        else {
            req.setAttribute("anafmessage",(anafAPIcompanyJSON.get("mesaj")));
            req.setAttribute("modalMessage", "Cannot get Data. Check your Internet connection.");
            req.setAttribute("modalShow", "block");
            req.setAttribute("pageToShowInTheMainBody", null);}
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/addCompany.jsp");
        dispatcher.forward(req, resp);}
}
