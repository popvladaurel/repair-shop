package ro.vlad.entities.client;

import org.junit.Test;
import ro.vlad.entities.Client;

/**
 * Created by vlad on 23.04.2017.
 */
public class PersonTest {
    @Test
    public void test_person_constructor() throws Exception {
        Client client1 = new Person("Vlad", "Pop", "1840822245033", "0747225588", "popvladaurel@gmail.com", "Romania", "Maramures", "Baia Mare", "B. St. Delavrancea", "31/20", "430292");
        System.out.println(client1.getId());
        System.out.println(client1.getName());

        Client client2 = new Person("Vlad", "Pop", "1840822245033", "0755228855", "popvladaurel@gmail.com");
        System.out.println(client2.getId());
        System.out.println(client2.getName());
    }


}