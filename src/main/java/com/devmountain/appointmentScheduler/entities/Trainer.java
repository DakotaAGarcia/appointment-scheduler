package com.devmountain.appointmentScheduler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Trainers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name="password", nullable = false, unique = true)
    private String password;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="bio", columnDefinition = "text")
    private String bio;
}
