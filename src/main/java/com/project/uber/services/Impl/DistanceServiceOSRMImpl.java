package com.project.uber.services.Impl;

import com.project.uber.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
//        call the third part api called OSRM to fetch the distance
        return 0;
    }
}
