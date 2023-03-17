package com.devmountain.appointmentScheduler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name="time", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="trainer_id", nullable = false)
    private Trainer trainer;
    @Column(name="description")
    private String description;
}
