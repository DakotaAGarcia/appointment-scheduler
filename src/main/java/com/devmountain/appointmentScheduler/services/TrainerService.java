package com.devmountain.appointmentScheduler.services;

import com.devmountain.appointmentScheduler.dtos.TrainerDto;
import com.devmountain.appointmentScheduler.entities.Trainer;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TrainerService {
    @Transactional
    List<String> addTrainer(TrainerDto trainerDto);

    List<String> trainerLogin(TrainerDto trainerDto);

    List<Trainer> getAllTrainers();
}
