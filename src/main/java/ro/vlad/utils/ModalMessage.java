package ro.vlad.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Builds the basic structure of an alert message
 */
public class ModalMessage {
    private Color color;
    private String modalMessage;
    private String url;
    public enum Color {RED("rgba(215, 0, 0, 0.9)"), GREEN("rgba(34, 187, 69, 1)"), BLUE("rgba(0, 0, 215, 0.9)");
        private String color;
        Color(String color) {}}

    /**
     * The ModalMessage constructor
     * @param color the color of the message body
     * @param modalMessage text to display inside the message
     * @param url path of the page to display in the main page body
     */
    public ModalMessage(Color color, String modalMessage, String url) {
        this.color = color;
        this.modalMessage = modalMessage;
        this.url = url;}

    public static void setReqModalMessage(HttpServletRequest req, ModalMessage modalMessage) {
        req.setAttribute("modalMessage", modalMessage.modalMessage);
        req.setAttribute("modalColor", modalMessage.color);
        req.setAttribute("modalShow", "block");
        req.setAttribute("pageToShowInTheMainBody", modalMessage.url);}

//TODO implement constructor and methods for a confirmation message
}
