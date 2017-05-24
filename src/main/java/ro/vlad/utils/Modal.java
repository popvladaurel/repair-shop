package ro.vlad.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Builds the basic structure of an alert/confirmation message
 */
public class Modal {
    private Color color;
    private String modalText;
    private String url;
    public enum Color {RED("rgba(215, 0, 0, 0.9)"), GREEN("rgba(34, 187, 69, 1)"), BLUE("rgba(0, 0, 215, 0.9)"), YELLOW("rgba(255, 235, 59, 1)");
        private String color;
        Color(String color) {}}

    /**
     * The Modal constructor
     * @param color the color of the message body
     * @param modalText text to display inside the message
     * @param url path of the page to display in the main page body
     */
    public Modal(Color color, String modalText, String url) {
        this.color = color;
        this.modalText = modalText;
        this.url = url;}

    public static void setMessage(HttpServletRequest req, Modal modal) {
        req.setAttribute("modal", modal.modalText);
        req.setAttribute("modalColor", modal.color);
        req.setAttribute("modalShow", "block");
        req.setAttribute("modalButtonShow", "none");
        req.setAttribute("pageToShowInTheMainBody", modal.url);}

    public static void setMessage(HttpServletRequest req, Modal modal, String servletPath) {
        setMessage(req, modal);
        req.setAttribute("modalButtonShow", "block");
        req.setAttribute("servletPath", servletPath);}

//TODO implement constructor and methods for a confirmation message
}
