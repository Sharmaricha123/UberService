package com.project.uber.strategies;


import com.project.uber.strategies.Impl.DriverMatchingHighestRatedDriverStrategy;
import com.project.uber.strategies.Impl.DriverMatchingNearestDriverStrategy;
import com.project.uber.strategies.Impl.RideFareSurgePricingFareCalculationStrategy;
import com.project.uber.strategies.Impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    public  DriverMatchingStrategy driverMatchingStrategy(double riderRating)
    {
        if (riderRating>=4.8)
        {
            return highestRatedDriverStrategy;
        }else {
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
//        6PM TO 9PM ---SUREG TIME

        LocalTime surgeStartTime= LocalTime.of(18,0);
        LocalTime surgeEndTime=LocalTime.of(21,0);
        LocalTime currentTime=LocalTime.now();

        boolean isSurgeTime=currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurgeTime)
        {
            return surgePricingFareCalculationStrategy;
        }else {
            return defaultFareCalculationStrategy;
        }
    }

}
