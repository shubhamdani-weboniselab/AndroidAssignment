package com.webonise.assignment11.asynctaskplusvolly;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class ServerResponse {
    static InputStream is = null;
    static JSONObject jsonObject = null;


    // constructor
    public ServerResponse() {

    }

    public JSONObject getJSONResponse(String url) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            int code = statusLine.getStatusCode();
            if (code == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                line = sb.toString();
                jsonObject = new JSONObject(line);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return JSON String
        return jsonObject;

    }
}
