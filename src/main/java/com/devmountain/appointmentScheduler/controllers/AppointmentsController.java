package com.devmountain.appointmentScheduler.controllers;

import com.devmountain.appointmentScheduler.dtos.AppointmentDto;
import com.devmountain.appointmentScheduler.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping("/user/{userId}")
    public List<AppointmentDto> getAppointmentsByUser(@PathVariable Long userId){
        return appointmentService.getAllAppointmentsByUserId(userId);
    }
    @PostMapping("/user/{userId}")
    public void addAppointment(@RequestBody AppointmentDto appointmentDto, @PathVariable Long userId){
        appointmentService.addAppointment(appointmentDto, userId);
    }
    @DeleteMapping("/{appointmentId}")
    public void deleteAppointmentById(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointmentById(appointmentId);
    }
    @PutMapping
    public void updateAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.updateAppointmentById(appointmentDto);
    }
    @GetMapping("/{appointmentId}")
    public Optional<AppointmentDto> getAppointmentById(@PathVariable Long appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }
}
