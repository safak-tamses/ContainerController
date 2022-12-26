package org.example;

import org.json.JSONArray;

import java.io.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String containerId;
        containerId = args[0];
        Process process = Runtime.getRuntime().exec("docker logs " + containerId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));



        String url = "http://192.168.1.231:5044/";


        FileWriter writer = new FileWriter("log.txt");

        HttpClientExample obj = new HttpClientExample();


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
            System.out.println(jsonArray.get(i));
        }


        obj.sendPost(lines);


        writer.close();
        process.waitFor();
    }
}







