package com.devmountain.appointmentScheduler.entities;

import com.devmountain.appointmentScheduler.dtos.AppointmentDto;
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

    public Appointment(AppointmentDto appointmentDto){
        if(appointmentDto.getDescription() != null)
            this.description = appointmentDto.getDescription();
        if(appointmentDto.getId() != null){
            this.id = appointmentDto.getId();
        }
        if(appointmentDto.getDate() != null){
            this.date = appointmentDto.getDate();
        }
        if(appointmentDto.getTime() != null){
            this.time = appointmentDto.getTime();
        }
        if (appointmentDto.getTrainerId() != null) {
            this.trainer = new Trainer();
            this.trainer.setId(appointmentDto.getTrainerId());
        }
        if (appointmentDto.getUserId() != null) {
            this.user = new User();
            this.user.setId(appointmentDto.getUserId());
        }
    }

}
