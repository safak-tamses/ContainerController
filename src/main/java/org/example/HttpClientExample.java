package org.example;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientExample {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    public void close() throws IOException {
        httpClient.close();
    }


    public void sendPost(List<String> data) throws Exception {

        HttpPost post = new HttpPost("http://192.168.1.231:5044");

        // add request parameter, form parameters
        List<String> dataEntity = new ArrayList<>();
        String jsonhead = "{\"devices\":[ \n";
        String jsontail = "]}";
        String json = jsonhead;

        for (int j = 0; j < data.size(); j++) {
            if (j == data.size() - 1) {
                json = json + data.get(j);
            } else {
                json = json + data.get(j) + ",\n";
            }
        }
//        for (String v : data) {
//            dataEntity.add(v);
//            json = json + v + ",\n" ;
//            data.remove(v);
//
//        }
        json = json + jsontail;
        post.setEntity(new StringEntity(json));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }



    }

}