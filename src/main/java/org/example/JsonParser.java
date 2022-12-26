package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public List<String> JsonEntityTurn(List<String> jsonData, List<String> dataTagName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> jsonDatas = new ArrayList<>();
        for (String jsonDatum : jsonData) {
            JsonNode rootNode = mapper.readTree(jsonDatum);
            for (String s : dataTagName) {
                jsonDatas.add(rootNode.get(s).asText());
            }
        }

        return jsonDatas;
    }
}


