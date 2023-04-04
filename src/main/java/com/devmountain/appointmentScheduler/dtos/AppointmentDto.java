package com.devmountain.appointmentScheduler.dtos;

import com.devmountain.appointmentScheduler.entities.Appointment;
import com.devmountain.appointmentScheduler.entities.Trainer;
import com.devmountain.appointmentScheduler.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto implements Serializable {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long userId;
    private String username;
    private Long trainerId;
    private String description;

    private String comment;

    public AppointmentDto(Appointment appointment){
        if(appointment.getId() != null){
            this.id = appointment.getId();
        }
        if(appointment.getDate() != null){
            this.date = appointment.getDate();
        }
        if(appointment.getTime() != null){
            this.time = appointment.getTime();
        }
        if(appointment.getTrainer() != null){
            this.trainerId = appointment.getTrainer().getId();
        }
        if(appointment.getUser() != null){
            this.userId = appointment.getUser().getId();
            this.username = appointment.getUser().getUsername();
        }
        if(appointment.getDescription() != null){
            this.description = appointment.getDescription();
        }
        if(appointment.getComment() != null){
            this.comment = appointment.getComment();
        }
    }
}
