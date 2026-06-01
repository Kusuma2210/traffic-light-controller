package com.natwest.trafficlight.service;

import com.natwest.trafficlight.model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficeLightServiceTest {
    private final TrafficLightService service;

    public TrafficeLightServiceTest(TrafficLightService service) {
        this.service = service;
    }
    @Test
    void shouldChangeSignalToEastWest(){
        service.changeSignal(Direction.EAST_WEST);
        String state = service.getCurrentState().getStates().get("EAST_WEST");
        assertEquals("GREEN",state);
    }
    @Test
    void shouldPauseController(){
        service.pause();
    }
    @Test
    void shouldResumeController(){
        service.resume();
    }

    @Test
    void shouldReturnCurrentState(){
        assertNotNull(service.getCurrentState());
    }
    @Test
    void shouldReturnSignalHistory(){
        service.pause();
        assertFalse(service.getHistory().isEmpty());
    }
}
