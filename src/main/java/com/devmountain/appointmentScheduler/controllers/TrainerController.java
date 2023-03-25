package com.devmountain.appointmentScheduler.controllers;

import com.devmountain.appointmentScheduler.dtos.TrainerDto;
import com.devmountain.appointmentScheduler.entities.Trainer;
import com.devmountain.appointmentScheduler.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addTrainer(@RequestBody TrainerDto trainerDto){
        String passHash = passwordEncoder.encode(trainerDto.getPassword());
        trainerDto.setPassword(passHash);
        return trainerService.addTrainer(trainerDto);
    }

    @PostMapping("/login")
    public List<String> trainerLogin(@RequestBody TrainerDto trainerDto){
        return trainerService.trainerLogin(trainerDto);
    }

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }
    @GetMapping("/{trainerId}")
    public ResponseEntity<TrainerDto> getTrainerById(@PathVariable Long trainerId) {
        TrainerDto trainerDto = trainerService.getTrainerById(trainerId);
        if (trainerDto != null) {
            return ResponseEntity.ok(trainerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
