package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LogCollector {
    public List<String> collector(String containerId) throws IOException, InterruptedException {
        List<String> collectedData = new ArrayList<>();
        Process process = Runtime.getRuntime().exec("docker logs" + containerId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            collectedData.add(line);
            System.out.println(line);
        }
        process.waitFor();
        return collectedData;
    }
}
