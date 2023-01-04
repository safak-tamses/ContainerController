package org.example;

import java.io.*;
import java.net.*;

import org.json.JSONException;
import org.json.JSONObject;

public class ScannerParameterGet {
    public ScannerParameterGet() {
    }

    public JSONObject getJsonParam(String urli) {
        try {
            URL url = new URL(urli);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            JSONObject json = new JSONObject(result.toString());
            return json;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}


