package com.project.uber.repositories;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Ride;
import com.project.uber.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepositoy extends JpaRepository<Ride,Long> {
    Page<Ride> findByRider(Rider rider, PageRequest pageRequest);

    Page<Ride> findByDriver(Driver driver, PageRequest pageRequest);
}
