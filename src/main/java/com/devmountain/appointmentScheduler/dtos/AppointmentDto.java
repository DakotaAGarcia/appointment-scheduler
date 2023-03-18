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
    private Long Id;
    private LocalDate date;
    private LocalTime time;
    private User user;
    private Trainer trainer;
    private String description;

//    public AppointmentDto(Appointment appointment){
//        if(appointment.getId() != null){
//            this.id = appointment.getId();
//        }
//        if(appointment.get)
//    }
}
