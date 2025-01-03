package com.project.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

    private Long id;
    private UserDto user;
    private  Double rating;
    private Boolean available;
    private String vehicleId;

}
