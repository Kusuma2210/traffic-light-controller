package com.natwest.trafficlight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalHistory {
    private String event;
    private LocalDateTime timestamp;
}
