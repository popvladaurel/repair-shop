package ro.vlad.entities.client;

public class ContactDetails {
    private String phoneNumber;
    private String email;

    public ContactDetails(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getContactDetails() {
        return "\n E-mail: " + email +
                "\n Phone: " + phoneNumber;
    }
}
