package com.example.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReachPoints {
    public static Map<String, Integer> pointsReached = new HashMap<>();

    public static Integer addAndGetPoint(String pointName) {

        Integer point = pointsReached.get(pointName);
        point++;
        pointsReached.put(pointName, point);
        return point;

    }

    public static void initPointReached(List<String> pointNames) {
        for (var name : pointNames) {
            pointsReached.put(name, 0);
        }
    }
}
