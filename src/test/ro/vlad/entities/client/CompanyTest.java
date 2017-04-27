package ro.vlad.entities.client;

import org.junit.Test;
import ro.vlad.entities.Client;

public class CompanyTest {
    @Test
    public void test_company_constructor() throws Exception {
        Client company = new Company("Terminus Computers SRL", "118524", "RO70BTRL76465421XX", "0262225588", "terminus@yahoo.ro",
                "Romania", "Maramures", "Baia Mare", "Ge. Cosbuc", "9", "435222",
                "Vlad", "Pop", "1840822245034", "07225588", "popvladaurel@gmail.com");
    }

}