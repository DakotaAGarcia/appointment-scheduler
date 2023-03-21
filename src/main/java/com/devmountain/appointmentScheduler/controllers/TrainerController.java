package com.devmountain.appointmentScheduler.controllers;

import com.devmountain.appointmentScheduler.dtos.TrainerDto;
import com.devmountain.appointmentScheduler.entities.Trainer;
import com.devmountain.appointmentScheduler.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/trainer")
    public List<String> addTrainer(@RequestBody TrainerDto trainerDto){
        String passHash = passwordEncoder.encode(trainerDto.getPassword());
        trainerDto.setPassword(passHash);
        return trainerService.addTrainer(trainerDto);
    }

    @PostMapping("/login/trainer")
    public List<String> trainerLogin(@RequestBody TrainerDto trainerDto){
        return trainerService.trainerLogin(trainerDto);
    }

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }


}
