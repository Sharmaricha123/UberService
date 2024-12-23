package com.project.uber.repositories;

import com.project.uber.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


// ST_DISTNACE(point1,point2)
// ST_DWITHIN(point1,point2)

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

//    @Query("SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
//            "FROM driver as d " +
//            "where available = true AND ST_DWithin(d.current_Location, :pickupLocation, 1000 " +
//             "LIMIT 10",nativeQuery=true
//    )
@Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
        "FROM driver d " +
        "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
        "ORDER BY distance " +
        "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickupLocation);
}
