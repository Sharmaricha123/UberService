package com.project.uber.entities;

import com.project.uber.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ElementCollection(fetch=FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}