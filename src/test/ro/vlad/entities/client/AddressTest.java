package ro.vlad.entities.client;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vlad on 23.04.2017.
 */
public class AddressTest {
    @Test
    public void test_address_constructor() throws Exception {
        Address address = new Address("Romania", "Maramures", "Baia Mare", "B. St. Delavrancea", "31/20", "430292");
        System.out.println(address.getAddress());
        Assert.assertEquals("\n Country: Romania" +
                "\n County: Maramures" +
                "\n City: Baia Mare" +
                "\n Street: B. St. Delavrancea" +
                "\n Number: 31/20" +
                "\n Postal Code: 430292", address.getAddress());
    }

}