package ro.vlad.entities.client;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vlad on 23.04.2017.
 */
public class ContactDetailsTest {
    @Test
    public void test_contactDetails_constructor() throws Exception {
        ContactDetails contactDetails = new ContactDetails("0747225588", "popvladaurel@gmail.com");
        System.out.println(contactDetails.getContactDetails());
        Assert.assertEquals("\n E-mail: popvladaurel@gmail.com" + "\n Phone: 0747225588", contactDetails.getContactDetails());
    }

}