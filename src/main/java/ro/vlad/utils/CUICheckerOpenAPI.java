package ro.vlad.utils;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class CUICheckerOpenAPI {
    public static String key;

    public void setKey(String key) {this.key = key;}

    public static JSONObject checkCUI(String CUI) {
        JSONObject companyJSON = new JSONObject();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet("https://api.openapi.ro/api/companies/" + CUI);
            request.addHeader("x-api-key", key);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
                BufferedReader output = new BufferedReader(inputStreamReader);
                companyJSON = new JSONObject(output.readLine());
                companyJSON.put("valid", true);
                return companyJSON;}
            else {
                companyJSON.put("valid", false);
                companyJSON.put("mesaj", "OpenAPI says: " + response.getStatusLine().getReasonPhrase() + " -> " + response.getStatusLine().getStatusCode());
                return companyJSON;}}
        catch (IOException e) {
            companyJSON.put("valid", false);
            companyJSON.put("mesaj", "Nu am putut prelua datele de la OpenAPI.");
            return companyJSON;}}
}
