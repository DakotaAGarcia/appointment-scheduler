package com.devmountain.appointmentScheduler.services;

import com.devmountain.appointmentScheduler.dtos.TrainerDto;
import com.devmountain.appointmentScheduler.entities.Trainer;
import com.devmountain.appointmentScheduler.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addTrainer(TrainerDto trainerDto){
        List<String> response = new ArrayList<>();
        Trainer trainer = new Trainer(trainerDto);
        trainerRepository.saveAndFlush(trainer);
        response.add("http://localhost:8080/trainer-login.html");
        return response;
    }
    @Override
    public List<String> trainerLogin(TrainerDto trainerDto){
        List<String> response = new ArrayList<>();
        Optional<Trainer> trainerOptional = trainerRepository.findByUsername(trainerDto.getUsername());
        if(trainerOptional.isPresent()){
            if(passwordEncoder.matches(trainerDto.getPassword(), trainerOptional.get().getPassword())){
                response.add("http://localhost:8080/trainer-home.html");
                response.add(trainerOptional.get().getId().toString());
            } else {
                response.add("Username or password incorrect");
            }
            }else {
            response.add("Username or password incorrect");
        }
        return response;
    }
    @Override
    public List<Trainer> getAllTrainers(){
        return trainerRepository.findAll();
    }
    @Override
    public TrainerDto getTrainerById(Long trainerId) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(trainerId);
        return trainerOptional.map(TrainerDto::new).orElse(null);
    }
    @Override
    @Transactional
    public void updateTrainerComment(Long trainerId, String comment) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(trainerId);
        if (trainerOptional.isPresent()) {
            Trainer trainer = trainerOptional.get();
            trainer.setComment(comment);
            trainerRepository.saveAndFlush(trainer);
        }
    }

}
