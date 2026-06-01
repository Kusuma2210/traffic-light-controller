package com.natwest.trafficlight.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Intersection {
    private String intersectionId;
    private Map<Direction,TrafficLight> signals;

    public Intersection(String intersectionId){
        this.intersectionId = intersectionId;

        this.signals = new ConcurrentHashMap<>();

        signals.put(Direction.NORTH_SOUTH,new TrafficLight(Direction.NORTH_SOUTH,LightState.GREEN));
        signals.put(Direction.NORTH_SOUTH, new TrafficLight(Direction.EAST_WEST,LightState.RED));
    }

    public String getIntersectionId(){
        return intersectionId;
    }
    public Map<Direction,TrafficLight> getSignals(){
        return signals;
    }
}
