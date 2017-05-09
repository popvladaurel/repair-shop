package ro.vlad.utils;

import org.junit.Test;

import static java.lang.Thread.sleep;

public class CUICheckerAnafAPITest {
    @Test
    public void test_anaf_POST_connection() throws Exception {
        System.out.println(CUICheckerAnafAPI.checkCUI("14399840"));
        sleep(2000);
    }

}