package ro.vlad.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CUICheckerAnafAPI {
    public static JSONObject checkCUI(String CUI) {
        JSONObject companyJSON = new JSONObject();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost("https://webservicesp.anaf.ro:/PlatitorTvaRest/api/v1/ws/tva");
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity("[{\"cui\": " + CUI + ", \"data\":\"2017-04-24\"}]"));
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
                BufferedReader output = new BufferedReader(inputStreamReader);
                companyJSON = new JSONObject(output.readLine());
                JSONArray companyDetailsFound = companyJSON.getJSONArray("found");
                JSONArray companyDetailsNotFound = companyJSON.getJSONArray("notfound");
                companyJSON = (companyDetailsFound.isNull(0) ? companyDetailsNotFound.getJSONObject(0) : companyDetailsFound.getJSONObject(0));
                companyJSON.put("valid", true);
                return companyJSON;}
            else {
                companyJSON.put("valid", false);
                companyJSON.put("mesaj", "ANAF says: " + response.getStatusLine().getReasonPhrase() + " -> " + response.getStatusLine().getStatusCode());
                return companyJSON;}}
        catch (IOException e) {
            companyJSON.put("valid", false);
            companyJSON.put("mesaj", "Nu am putut prelua datele de la ANAF.");
            return companyJSON;}}
}
