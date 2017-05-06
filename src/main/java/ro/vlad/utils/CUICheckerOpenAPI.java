package ro.vlad.utils;


import org.apache.http.HttpResponse;
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
    public static JSONObject checkCUI(String CUI) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet("https://api.openapi.ro/api/companies/" + CUI);
            request.addHeader("x-api-key", "TjfuGWLsuH7is4QiT31o9SrywytsPnNvsjNUVz1WEsfvW1mVgw");
            HttpResponse response = httpClient.execute(request);
            BufferedReader output = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            JSONObject companyJSON = new JSONObject(output.readLine());
            return companyJSON;}
        catch (JSONException e) {
            System.out.println("No Internet connection or OpenAPI subscription expired.");
            return null;}
        catch (NullPointerException e) {
            System.out.println("NullPointerException CUI invalid. " + CUI);
            return null;}
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;}
        catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;}
        catch (IOException e) {
            e.printStackTrace();
            return null;}}
}
