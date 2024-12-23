package com.project.uber.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Getter
@Setter
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private Double rating;

    private Boolean available;

    private String vehicleId;

    @Column(columnDefinition = "Geometry(Point,4326)")
    Point currentLocation;


}
