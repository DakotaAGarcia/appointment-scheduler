package com.devmountain.appointmentScheduler.entities;

import com.devmountain.appointmentScheduler.dtos.TrainerDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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


    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Appointment> appointmentSet = new HashSet<>();

    public Trainer(TrainerDto trainerDto){
        if(trainerDto.getUsername() != null)
            this.username = trainerDto.getUsername();
        if(trainerDto.getPassword() != null)
            this.password = trainerDto.getPassword();
    }
}
