package com.natwest.trafficlight.model;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Intersection {
    private String intersectionId;
    private Map<Direction,TrafficLight> signals;

    public Intersection(String intersectionId){
        this.intersectionId = intersectionId;

        this.signals = new ConcurrentHashMap<>();

        signals.put(Direction.NORTH_SOUTH,new TrafficLight(Direction.NORTH_SOUTH,LightState.GREEN));
        signals.put(Direction.EAST_WEST, new TrafficLight(Direction.EAST_WEST,LightState.RED));
    }

}
