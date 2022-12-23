package org.example;

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
        String line;
        String url = "http://192.168.1.231:5044/";

        FileWriter writer = new FileWriter("log.txt");

        HttpClientExample obj = new HttpClientExample();
        List<String> lines = new ArrayList<String>();
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
        obj.sendPost(lines);
        writer.close();
        process.waitFor();
    }
}







