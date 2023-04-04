package com.devmountain.appointmentScheduler.services;

import com.devmountain.appointmentScheduler.dtos.AppointmentDto;
import com.devmountain.appointmentScheduler.entities.Appointment;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<AppointmentDto> getAllAppointmentsByUserId(Long userId);
    List<AppointmentDto> getAppointmentsByTrainer(Long trainerId);

    @Transactional
    void addAppointment(AppointmentDto appointmentDto, Long userId);
    @Transactional
    void deleteAppointmentById(Long appointmentId);
    @Transactional
    void updateAppointmentById(AppointmentDto appointmentDto, Long appointmentId);

    Optional<AppointmentDto> getAppointmentById(Long appointmentId);

    String findCommentByAppointmentId(Long appointmentId);

    boolean updateAppointmentComment(Long appointmentId, String comment);

}
