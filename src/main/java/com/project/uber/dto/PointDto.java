package com.project.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PointDto {

    private double[] coordinates;
    private String type="Point";

    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
