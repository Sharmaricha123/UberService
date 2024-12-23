package com.project.uber.dto;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Rider;
import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {

    private Long id;


    private Point pickupLocation;


    private Point dropOffLocation;


    private LocalDateTime createdTime;


    private Rider rider;


    private DriverDto driver;

    private PaymentMethod paymentMethod;


    private RideStatus rideStatus;

    private String otp;

    private  Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
