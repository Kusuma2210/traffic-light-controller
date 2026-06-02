package com.natwest.trafficlight.service;

import com.natwest.trafficlight.dto.CurrentStateResponse;
import com.natwest.trafficlight.exception.ControllerPausedException;
import com.natwest.trafficlight.exception.IntersectionNotFoundException;
import com.natwest.trafficlight.model.*;
import org.springframework.boot.webmvc.autoconfigure.DispatcherServletRegistrationBean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TrafficLightServiceImpl implements TrafficLightService {
    private final Map<String, Intersection> intersections = new ConcurrentHashMap<>();
    private final List<SignalHistory> history = new CopyOnWriteArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();  ;
    private volatile boolean paused = false;

    public TrafficLightServiceImpl(){
       intersections.put("JUNCTION_1", new Intersection("JUNCTION_1"));
       intersections.put("JUNCTION_2", new Intersection("JUNCTION_2"));

    }
    @Override
    public void changeSignal(Direction direction,String intersectionId) {
        lock.lock();
        try{
            if(paused){
                throw new ControllerPausedException();
            }

            Intersection intersection = intersections.get(intersectionId);
            if(intersection == null){
                throw new IntersectionNotFoundException(intersectionId);
            }
            Map<Direction,TrafficLight> signals = intersection.getSignals();
            if(direction == Direction.NORTH_SOUTH){
                signals.get(Direction.NORTH_SOUTH).setState(LightState.GREEN);
                signals.get(Direction.EAST_WEST).setState(LightState.RED);
                history.add(new SignalHistory("Changed to " + direction,LocalDateTime.now()));
            }
            else{
                signals.get(Direction.EAST_WEST).setState(LightState.RED);
                signals.get(Direction.NORTH_SOUTH).setState(LightState.YELLOW);
                history.add(new SignalHistory("Changed to "+ direction,LocalDateTime.now()));
            }
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void pause() {
     paused = true;
     history.add(new SignalHistory("Controller Paused", LocalDateTime.now()));
    }

    @Override
    public void resume() {
        paused = false;
        history.add(new SignalHistory("Controller Resumed", LocalDateTime.now()));
    }

    @Override
    public CurrentStateResponse getCurrentState(String intersectionId) {
       Intersection intersection = intersections.get(intersectionId);
       if(intersection == null){
           throw new IntersectionNotFoundException(intersectionId);
       }
        Map<String, String> result = new HashMap<>();
        intersection.getSignals().forEach((k,v) -> result.put(k.name(),v.getState().name()));
        return new CurrentStateResponse(result,intersectionId);
    }

    @Override
    public List<SignalHistory> getHistory() {
        return history;
    }
}
