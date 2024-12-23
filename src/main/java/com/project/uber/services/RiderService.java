package com.project.uber.services;

import com.project.uber.dto.RideDto;
import com.project.uber.dto.RideRequestDto;
import com.project.uber.dto.RiderDto;
import com.project.uber.entities.Rider;
import com.project.uber.entities.User;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    RiderDto rateDriver(Long rideId,Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
