package com.project.uber.repositories;

import com.project.uber.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepositoy extends JpaRepository<Ride,Long> {
}
