package ro.vlad.utils;

import org.junit.Test;
import java.util.UUID;

public class GenerateUUIDTest {

    @Test
    public void generate_UUID() {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
    }
}
