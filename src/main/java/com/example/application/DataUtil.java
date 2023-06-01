package com.example.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataUtil {

    private static List<Data> data;

    public static List<Data> getData() {
        if (data == null) {
            loadData();
        }
        return data;
    }

    public static Data getByEndpoint(String endpoint) {
        if (data == null) {
            loadData();
        }
        return data.stream().filter(d -> Objects.equals(d.getEndpoint(), endpoint)).findFirst().orElseThrow();
    }

    public static List<String> getAllEndpoints() {
        if (data == null) {
            loadData();
        }
        return data.stream().map(Data::getEndpoint).collect(Collectors.toList());
    }

    public static List<String> getAllPoints(){
        if (data == null){
            loadData();
        }
        return data.stream().map(Data::getPoint).collect(Collectors.toList());
    }


    private static void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = DataUtil.class.getResourceAsStream("/data.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            data = objectMapper.readValue(contents, new TypeReference<List<Data>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
