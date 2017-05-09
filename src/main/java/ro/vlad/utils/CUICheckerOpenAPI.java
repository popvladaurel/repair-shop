package ro.vlad.utils;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ro.vlad.persistence.JpaListener.LOGGER;

/**
 * Same as CUICheckerAnafAPI, but this one uses OpenAPI to retireve data
 * Also provides a method of changing the key required for valid interrogations
 */
public class CUICheckerOpenAPI {
    public static String key;

    public static JSONObject checkCUI(String CUI) {
        JSONObject companyJSON = new JSONObject();
        if (key == null) {
            LOGGER.warn("Empty OpenAPI key, please change it.");
            companyJSON.put("valid", false);
            companyJSON.put("mesaj", "Invalid OpenAPI key.");
            return companyJSON;}
        else {
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
                HttpGet request = new HttpGet("https://api.openapi.ro/api/companies/" + CUI);
                request.addHeader("x-api-key", key);
                HttpResponse response = httpClient.execute(request);
                LOGGER.info("Received OpenAPI response: " + response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStreamReader inputStreamReader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
                    BufferedReader output = new BufferedReader(inputStreamReader);
                    companyJSON = new JSONObject(output.readLine());
                    companyJSON.put("valid", true);
                    companyJSON.put("mesaj", companyJSON.get("stare"));
                    return companyJSON;}
                else {
                    LOGGER.warn("OpenAPI refused to honor the request: " + response.getStatusLine().getReasonPhrase() + " -> " + response.getStatusLine().getStatusCode());
                    companyJSON.put("valid", false);
                    companyJSON.put("mesaj", "OpenAPI says: " + response.getStatusLine().getReasonPhrase() + " -> " + response.getStatusLine().getStatusCode());
                    return companyJSON;}}
            catch (IOException e) {
                LOGGER.warn("could not connect. Check internet connection.");
                companyJSON.put("valid", false);
                companyJSON.put("mesaj", "Nu am putut prelua datele de la OpenAPI.");
                return companyJSON;}}}

    public static void setKey(String newOpenApiKey) {
        key = newOpenApiKey;
        LOGGER.info("OpenAPI key changed!");}

    public static String getKey() {
        LOGGER.info("Present OpenAPI key: " + key);
        return (key != null ? key : "No key stored. Try adding a key.");}
}
