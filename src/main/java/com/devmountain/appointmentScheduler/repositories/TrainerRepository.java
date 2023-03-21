package com.devmountain.appointmentScheduler.repositories;

import com.devmountain.appointmentScheduler.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByUsername(String username);
}
