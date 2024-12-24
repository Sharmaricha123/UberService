package com.project.uber.dto;

import com.project.uber.entities.Rider;
import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {

    private Long id;


    private PointDto pickupLocation;


    private PointDto dropOffLocation;


    private LocalDateTime createdTime;


    private RiderDto rider;


    private DriverDto driver;

    private PaymentMethod paymentMethod;


    private RideStatus rideStatus;

    private String otp;

    private  Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
