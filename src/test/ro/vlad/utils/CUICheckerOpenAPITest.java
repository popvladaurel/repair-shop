package ro.vlad.utils;

import org.junit.Test;

import static java.lang.Thread.sleep;

public class CUICheckerOpenAPITest {
    @Test
    public void test_openapi_connection() throws Exception {
        System.out.println(CUICheckerOpenAPI.checkCUI("14399840"));
        sleep(2000);
        System.out.println(CUICheckerOpenAPI.checkCUI("29216127"));
        sleep(2000);
        System.out.println(CUICheckerOpenAPI.checkCUI("29216475"));
        sleep(2000);
        System.out.println(CUICheckerOpenAPI.checkCUI("34526760"));
        sleep(2000);
    }
}