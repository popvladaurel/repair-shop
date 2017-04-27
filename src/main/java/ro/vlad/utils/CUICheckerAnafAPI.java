package ro.vlad.utils;

import org.apache.http.HttpResponse;
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
    public static String checkCUI(String CUI) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost("https://webservicesp.anaf.ro:/PlatitorTvaRest/api/v1/ws/tva");
            request.addHeader("content-type", "application/json");
            request.setEntity(new StringEntity("[{\"cui\": " + CUI + ", \"data\":\"2017-04-24\"}]"));
            HttpResponse response = httpClient.execute(request);
            BufferedReader output = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            JSONObject companyJSON = new JSONObject(output.readLine());
            JSONArray companyDetailsFound = companyJSON.getJSONArray("found");
            JSONArray companyDetailsNotFound = companyJSON.getJSONArray("notfound");
            companyJSON = (companyDetailsFound.isNull(0) ? companyDetailsNotFound.getJSONObject(0) : companyDetailsFound.getJSONObject(0));
            switch (companyJSON.get("mesaj").toString()) {
                case ("cui negasit"):
                    return companyJSON.get("cui")
                            + "\n\t" + companyJSON.get("mesaj")
                            + "\n";
                default:
                    return companyJSON.get("cui").toString()
                            + "\n\t" + companyJSON.get("denumire")
                            + "\n\t" + companyJSON.get("adresa")
                            + "\n\t" + companyJSON.get("mesaj")
                            + "\n";
            }
        } catch (IOException e) {
            return "\n No network connection or ANAF not responding.";
        }
    }
}
