package com.natwest.trafficlight.service;

import com.natwest.trafficlight.dto.CurrentStateResponse;
import com.natwest.trafficlight.model.Direction;
import com.natwest.trafficlight.model.SignalHistory;

import java.util.List;

public interface TrafficLightService {
    void changeSignal(Direction direction,String intersectionId);
    void pause();
    void resume();
    CurrentStateResponse getCurrentState(String intersectionId);
    List<SignalHistory> getHistory();
}
