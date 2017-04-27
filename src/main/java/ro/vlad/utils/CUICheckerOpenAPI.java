package ro.vlad.utils;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CUICheckerOpenAPI {
    public static String checkCUI(String CUI) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet("https://api.openapi.ro/api/companies/" + CUI);
            request.addHeader("x-api-key", "JdJd51oEJntBmx7Tttp8mByA56gRn3npV2aPXvxgRr1cjvBViw");
            HttpResponse response = httpClient.execute(request);
            BufferedReader output = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            JSONObject companyJSON = new JSONObject(output.readLine());
            return "\n" + companyJSON.get("denumire").toString()
                    + "\n\t" + companyJSON.get("cif")
                    + "\n\t" + companyJSON.get("numar_reg_com")
                    + "\n\t" + companyJSON.get("adresa")
                    + ", " + companyJSON.get("judet")
                    + "\n\t" + companyJSON.get("stare");
        } catch (JSONException e) {
            return "\n" + "CUI invalid."
                    + "\n\t" + CUI;
        } catch (IOException e) {
            return "\n No Internet connection or OpenAPI subscription expired.";
        }
    }
}
