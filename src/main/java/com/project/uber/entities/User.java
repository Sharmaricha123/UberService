package com.project.uber.entities;

import com.project.uber.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="app_user",indexes = {
        @Index(name = "idx_user_email",columnList = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @ElementCollection(fetch=FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
