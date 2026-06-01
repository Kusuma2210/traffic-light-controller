package com.natwest.trafficlight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentStateResponse {
    private Map<String,String> states;
    private String intersectionId;
}
