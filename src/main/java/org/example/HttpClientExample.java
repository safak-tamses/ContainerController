package org.example;


import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class HttpClientExample {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    public void close() throws IOException {
        httpClient.close();
    }


    public void sendPost(String url ,List<String> data) throws Exception {

        HttpPost post = new HttpPost(url);

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
        for (String v : data) {
            dataEntity.add(v);
            json = json + v + ",\n" ;
            data.remove(v);
        }

        json = json + jsontail;
        String unescapedJsonString = StringEscapeUtils.unescapeJava(json);
        System.out.println(unescapedJsonString);
        post.setEntity(new StringEntity(unescapedJsonString));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));

        }



    }

}