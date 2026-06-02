package com.natwest.trafficlight.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeSignalRequest {
    @NotBlank
    private String direction;
    @NotBlank
    private String intersectionId;
}
