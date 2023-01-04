package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String containerId;
//        containerId = args[0];
        Process startScanner= Runtime.getRuntime().exec("docker run --network host -t scanner arp 192.168.1.1/24 --json ");
//        Process process = Runtime.getRuntime().exec("docker logs " + containerId);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(startScanner.getInputStream()));


        String url = "http://192.168.1.133:5044/";


        FileWriter writer = new FileWriter("log.txt");

        HttpClientExample obj = new HttpClientExample();

/*Burası toplanan logları dosyaya kaydeden ve listeye ekleyip daha sonra listeyi yollamak için kullanılır*/
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                lines.add(line);
                writer.write(line);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                obj.close();
            }
        }




        List<String>tagname=new ArrayList<>();
        tagname.add("ip");
        tagname.add("mac");
        tagname.add("vendor");
        JsonParser jsonParser = new JsonParser();
        List<String>out;
        out=jsonParser.JsonEntityTurn(lines,tagname);

        JSONArray jsonArray = new JSONArray(out);

        for (int i = 0; i < jsonArray.length(); i++) {
            //System.out.println(jsonArray.get(i));
        }


        obj.sendPost(url,lines);


        writer.close();
        startScanner.waitFor();



//        /*Bu kısım belirli bir url üzerinden get methoduyla json verisi çekmeye yarıyor*/
//        ScannerParameterGet scannerParameterGet =new ScannerParameterGet();
//        JSONObject data=scannerParameterGet.getJsonParam("http://192.168.1.133:9200/logstash-2023.01.03/_search?q=*");
//        String userId = data.getString("hits");
//        System.out.println("hits: " + userId);
    }


}