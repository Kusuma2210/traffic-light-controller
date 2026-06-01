package com.natwest.trafficlight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeSignalRequest {
    private String direction;
    private String intersectionId;
}
