package com.natwest.trafficlight.controller;

import com.natwest.trafficlight.dto.ChangeSignalRequest;
import com.natwest.trafficlight.dto.CurrentStateResponse;
import com.natwest.trafficlight.model.Direction;
import com.natwest.trafficlight.model.SignalHistory;
import com.natwest.trafficlight.service.TrafficLightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traffic")
public class TrafficLightController {
    private final TrafficLightService service;

    public TrafficLightController(TrafficLightService service) {
        this.service = service;
    }
    @PostMapping("/change")
    public String changeSignal(@RequestBody ChangeSignalRequest request,String intersectionId){
        service.changeSignal(Direction.valueOf(request.getDirection()),request.getIntersectionId());
        return "Signal Changed";
    }
    @PostMapping("/pause")
    public String pause(){
        service.pause();
      return "Paused";
    }
    @PostMapping("/resume")
    public String resume(){
        service.resume();
        return "resumed";
    }
    @GetMapping("/state/{intersectionId}")
    public CurrentStateResponse state(@PathVariable String intersectionId){
        return service.getCurrentState(intersectionId);
    }
    @GetMapping("/history")
    public List<SignalHistory> getHistory(){
        return service.getHistory();
    }
}
