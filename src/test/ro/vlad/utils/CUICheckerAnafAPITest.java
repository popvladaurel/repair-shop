package ro.vlad.utils;

import org.junit.Test;

import static java.lang.Thread.sleep;

public class CUICheckerAnafAPITest {
    @Test
    public void test_anaf_POST_connection() throws Exception {
        System.out.println(CUICheckerAnafAPI.checkCUI("14399840"));
        sleep(2000);
        System.out.println(CUICheckerAnafAPI.checkCUI("29216127"));
        sleep(2000);
        System.out.println(CUICheckerAnafAPI.checkCUI("29216475"));
        sleep(2000);
        System.out.println(CUICheckerAnafAPI.checkCUI("34526760"));
        sleep(2000);
    }

}