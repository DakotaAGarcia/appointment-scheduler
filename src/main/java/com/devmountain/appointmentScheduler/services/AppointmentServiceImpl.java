package com.devmountain.appointmentScheduler.services;

import com.devmountain.appointmentScheduler.dtos.AppointmentDto;
import com.devmountain.appointmentScheduler.entities.Appointment;
import com.devmountain.appointmentScheduler.entities.User;
import com.devmountain.appointmentScheduler.entities.Trainer;
import com.devmountain.appointmentScheduler.repositories.AppointmentRepository;
import com.devmountain.appointmentScheduler.repositories.TrainerRepository;
import com.devmountain.appointmentScheduler.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TrainerRepository trainerRepository;


    @Override
    public void addAppointment(AppointmentDto appointmentDto, Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    Optional<Trainer> trainerOptional = trainerRepository.findById(appointmentDto.getTrainerId());
    Appointment appointment = new Appointment(appointmentDto);
    userOptional.ifPresent(appointment::setUser);
    trainerOptional.ifPresent(appointment::setTrainer);
    appointmentRepository.saveAndFlush(appointment);
    }

    @Transactional
    public void deleteAppointmentById(Long appointmentId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        appointmentOptional.ifPresent(appointment -> appointmentRepository.delete(appointment));
    }
    @Transactional
    public void updateAppointmentById(AppointmentDto appointmentDto, Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());

        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            System.out.println("Before update: " + appointment);

            appointment.setDescription(appointmentDto.getDescription());
            appointment.setDate(appointmentDto.getDate());
            appointment.setTime(appointmentDto.getTime());

            appointmentRepository.saveAndFlush(appointment);

            System.out.println("After update: " + appointment);
        } else {
            System.out.println("Appointment not found with ID: " + appointmentId);
        }
    }


    @Override
    public List<AppointmentDto> getAllAppointmentsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<Appointment> appointmentList = appointmentRepository.findAllByUserEquals(userOptional.get());
            return appointmentList.stream().map(appointment -> new AppointmentDto(appointment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    @Override
    public Optional<AppointmentDto> getAppointmentById(Long appointmentId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(appointmentOptional.isPresent()){
            return Optional.of(new AppointmentDto(appointmentOptional.get()));
        }
        return Optional.empty();
    }

}
