package ro.vlad.security;

import org.junit.Test;

public class PasswordStorageTest {

    @Test
    public void test_truncated_hash() {
        String userString = "password!";
        String goodHash = "";
        String badHash = "";
        int badHashLength = 0;
        try {
            goodHash = PasswordStorage.createHash(userString);}
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);}
        badHashLength = goodHash.length();
        do {
            badHashLength -= 1;
            badHash = goodHash.substring(0, badHashLength);
            boolean raised = false;
            try {
                PasswordStorage.verifyPassword(userString, badHash);}
            catch (PasswordStorage.InvalidHashException ex) {
                raised = true;}
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.exit(1);}
            if (!raised) {
                System.out.println("Truncated hash test: FAIL " + "(At hash length of " + badHashLength + ")");
                System.exit(1);}}
        while (badHash.charAt(badHashLength - 3) != ':');
        System.out.println("Truncated hash test: pass");}

    @Test
    public void test_basic_validation() {
        try {
            boolean failure = false;
            for(int i = 0; i < 10; i++) {
                String password = "" + i;
                String hash = PasswordStorage.createHash(password);
                String secondHash = PasswordStorage.createHash(password);
                if(hash.equals(secondHash)) {
                    System.out.println("FAILURE: TWO HASHES ARE EQUAL!");
                    failure = true;}
                String wrongPassword = ""+ (i + 1);
                if (PasswordStorage.verifyPassword(wrongPassword, hash)) {
                    System.out.println("FAILURE: WRONG PASSWORD ACCEPTED!");
                    failure = true;}
                if(!PasswordStorage.verifyPassword(password, hash)) {
                    System.out.println("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
                    failure = true;}}
            if(failure) {
                System.out.println("TESTS FAILED!");
                System.exit(1);}
            else {
                System.out.println("Validation test: pass");}}
        catch(Exception ex) {
            System.out.println("ERROR: " + ex);
            System.exit(1);}}

    @Test
    public void test_hash_function_checking() {
        try {
            String hash = PasswordStorage.createHash("foobar");
            hash = hash.replaceFirst("sha1:", "sha256:");
            boolean raised = false;
            try {
                PasswordStorage.verifyPassword("foobar", hash);}
            catch (PasswordStorage.CannotPerformOperationException ex) {
                raised = true;}
            if (raised) {
                System.out.println("Algorithm swap: pass");}
            else {
                System.out.println("Algorithm swap: FAIL");
                System.exit(1);}}
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);}}

    @Test
    public void test_hash_generation() {
        try {
            System.out.println(PasswordStorage.createHash("admin"));}
        catch (PasswordStorage.CannotPerformOperationException e) {e.printStackTrace();}}

    @Test
    public void test_hash_for_same_string() throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        if (PasswordStorage.verifyPassword("1234", "sha1:64000:18:f2eBH+nz+UxhlClfde6KS5XKH5m8pc9a:6E2B/1bq3SWfaFXt6rDKk501")) {
            System.out.println("OK");}
        if (PasswordStorage.verifyPassword("1234", "sha1:64000:18:RYVN5qIoZ/t08MpSw+0fsiZtyQT4Wpja:QLs36T28/P74AB/7nhVm8mCS")) {
            System.out.println("OK");}
        if (PasswordStorage.verifyPassword("1234", "sha1:64000:18:zazp7jC16IO09reJnegw3v/hY6ZfLTRA:tya39xaLh7A+c3Zll82yK6mA")) {
            System.out.println("OK");}
        if (PasswordStorage.verifyPassword("1234", "sha1:64000:18:DQRAsfToBCitptj8eIxWp12AyYiR1rJI:t87EZjGVae+fZI5zkyQkPLYo")) {
            System.out.println("OK");}
        if (PasswordStorage.verifyPassword("1234", "sha1:64000:18:Lt/wiqbeW8skxG0nkqFYcs7biA86zUgO:XPFg9kWUYbKkUZqHDM6xlcOR")) {
            System.out.println("OK");}}
}