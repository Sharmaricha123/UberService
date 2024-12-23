package com.project.uber.services.Impl;

import com.project.uber.dto.RideDto;
import com.project.uber.dto.RideRequestDto;
import com.project.uber.dto.RiderDto;
import com.project.uber.entities.RideRequest;
import com.project.uber.entities.Rider;
import com.project.uber.entities.User;
import com.project.uber.entities.enums.RideRequestStatus;
import com.project.uber.repositories.RideRequestRepository;
import com.project.uber.repositories.RiderRepository;
import com.project.uber.services.RiderService;
import com.project.uber.strategies.DriverMatchingStrategy;
import com.project.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private  final RideFareCalculationStrategy
            rideFareCalculationStrategy;
    private  final DriverMatchingStrategy driverMatchingStrategy;
    private  final RideRequestRepository rideRequestRepository;
    private  final RiderRepository riderRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest=modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        Double fare=rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest= rideRequestRepository.save(rideRequest);

        driverMatchingStrategy.findMatchingDriver(rideRequest);

        log.info(rideRequest.toString());

        return  modelMapper.map(savedRideRequest,RideRequestDto.class);
//        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }
}
