package ro.vlad.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Builds the basic structure of an alert/confirmation message
 */
public class Modal {
    private Colors color;
    private String modalText;
    private String url;


    /**
     * The Modal constructor
     * @param color the color of the message body
     * @param modalText text to display inside the message
     * @param url path of the page to display in the main page body
     */
    public Modal(Colors color, String modalText, String url) {
        this.color = color;
        this.modalText = modalText;
        this.url = url;}

    public static void setMessage(HttpServletRequest req, Modal modal) {
        req.setAttribute("modal", modal.modalText);
        req.setAttribute("modalColor", modal.color.toString());
        req.setAttribute("modalShow", "block");
        req.setAttribute("modalButtonShow", "none");
        req.setAttribute("pageToShowInTheMainBody", modal.url);}

    public static void setMessage(HttpServletRequest req, Modal modal, String servletPath) {
        setMessage(req, modal);
        req.setAttribute("modalButtonShow", "block");
        req.setAttribute("servletPath", servletPath);}

//TODO implement constructor and methods for a confirmation message
}
