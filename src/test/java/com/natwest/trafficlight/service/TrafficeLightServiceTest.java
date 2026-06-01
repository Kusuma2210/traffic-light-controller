package com.natwest.trafficlight.service;

import com.natwest.trafficlight.model.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TrafficeLightServiceTest {
    private final TrafficLightService service;

    @Autowired
    public TrafficeLightServiceTest(TrafficLightService service) {
        this.service = service;
    }
    @Test
    void shouldChangeSignalToEastWest(){
        service.changeSignal(Direction.EAST_WEST,"JUNCTION_1");
        String state = service.getCurrentState("JUNCTION_1").getStates().get("EAST_WEST");
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
        assertNotNull(service.getCurrentState("JUNCTION_1"));
    }
    @Test
    void shouldReturnSignalHistory(){
        service.pause();
        assertFalse(service.getHistory().isEmpty());
    }
}
