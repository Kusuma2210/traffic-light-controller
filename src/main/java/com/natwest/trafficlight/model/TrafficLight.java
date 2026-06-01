package com.natwest.trafficlight.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLight {
    private Direction direction;
    private LightState state;
}
