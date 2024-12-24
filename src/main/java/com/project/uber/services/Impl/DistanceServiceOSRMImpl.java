package com.project.uber.services.Impl;

import com.project.uber.services.DistanceService;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL =  "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
//        call the third part api called OSRM to fetch the distance

        try {
            String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
            OSRMResponseDto responseDto = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            return responseDto.getRoutes().get(0).getDistance() / 1000.0;
        }catch (Exception e)
        {
            throw new RuntimeException("Error getting data from OSRM"+e.getMessage());
        }
    }
}

@Getter
@Setter
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}

@Getter
@Setter
class OSRMRoute{
    private Double distance;
}
