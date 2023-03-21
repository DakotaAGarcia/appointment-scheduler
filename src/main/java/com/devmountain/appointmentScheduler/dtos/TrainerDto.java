package com.devmountain.appointmentScheduler.dtos;

import com.devmountain.appointmentScheduler.entities.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrainerDto implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Set<AppointmentDto> appointmentDtoSet = new HashSet<>();

    public TrainerDto(Trainer trainer){
        if(trainer.getId() != null) {
            this.id = trainer.getId();
        }
        if(trainer.getUsername() != null){
            this.username = trainer.getUsername();
        }
        if(trainer.getPassword() != null){
            this.password = trainer.getPassword();
        }
    }
}
