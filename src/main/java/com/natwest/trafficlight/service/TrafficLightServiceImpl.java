package com.natwest.trafficlight.service;

import com.natwest.trafficlight.dto.CurrentStateResponse;
import com.natwest.trafficlight.model.Direction;
import com.natwest.trafficlight.model.LightState;
import com.natwest.trafficlight.model.SignalHistory;
import com.natwest.trafficlight.model.TrafficLight;
import org.springframework.boot.webmvc.autoconfigure.DispatcherServletRegistrationBean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TrafficLightServiceImpl implements TrafficLightService {
    private final Map<Direction, TrafficLight> signals = new HashMap<>();
    private final List<SignalHistory> history = new CopyOnWriteArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();  ;
    private volatile boolean paused = false;

    public TrafficLightServiceImpl(DispatcherServletRegistrationBean dispatcherServletRegistration){
        signals.put(Direction.NORTH_SOUTH, new TrafficLight(Direction.NORTH_SOUTH, LightState.GREEN));
        signals.put(Direction.EAST_WEST, new TrafficLight(Direction.EAST_WEST, LightState.RED));

    }
    @Override
    public void changeSignal(Direction direction) {
        lock.lock();
        try{
            if(paused){
                throw new RuntimeException("Paused");
            }
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
    public CurrentStateResponse getCurrentState() {
        Map<String, String> result = new HashMap<>();
        signals.forEach((k,v) -> result.put(k.name(),v.getState().name()));
        return new CurrentStateResponse(result);
    }

    @Override
    public List<SignalHistory> getHistory() {
        return history;
    }
}
