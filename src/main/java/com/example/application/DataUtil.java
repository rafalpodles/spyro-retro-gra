package com.example.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
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

    public static Data getByEndpoint(String endpoint){
        if (data == null) {
            loadData();
        }
        return data.stream().filter(d-> Objects.equals(d.getEndpoint(), endpoint)).findFirst().orElseThrow();
    }

    public static List<String> getAllEndpoints(){
        if (data == null) {
            loadData();
        }
        return data.stream().map(Data::getEndpoint).collect(Collectors.toList());
    }


    private static void loadData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("src/main/resources/data.json");
            data = objectMapper.readValue(jsonFile, new TypeReference<List<Data>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
