package com.devmountain.appointmentScheduler.controllers;

import com.devmountain.appointmentScheduler.dtos.AppointmentDto;
import com.devmountain.appointmentScheduler.dtos.TrainerDto;
import com.devmountain.appointmentScheduler.entities.Appointment;
import com.devmountain.appointmentScheduler.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping("/user/{userId}")
    public List<AppointmentDto> getAppointmentsByUser(@PathVariable Long userId){
        List<AppointmentDto> appointments = appointmentService.getAllAppointmentsByUserId(userId);
        System.out.println("User ID: " + userId);
        System.out.println("Appointments: " + appointments);
        return appointments;
    }
    @PostMapping("/user/{userId}")
    public void addAppointment(@RequestBody AppointmentDto appointmentDto, @PathVariable Long userId){
        appointmentService.addAppointment(appointmentDto, userId);
    }
    @DeleteMapping("/{appointmentId}")
    public void deleteAppointmentById(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointmentById(appointmentId);
    }
    @PutMapping("/{appointmentId}")
    public void updateAppointment(@RequestBody AppointmentDto appointmentDto, @PathVariable Long appointmentId){
        appointmentService.updateAppointmentById(appointmentDto, appointmentId);
    }

    @GetMapping("/{appointmentId}")
    public Optional<AppointmentDto> getAppointmentById(@PathVariable Long appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }
    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByTrainer(@PathVariable Long trainerId) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByTrainer(trainerId);
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/{appointmentId}/comment")
    public ResponseEntity<String> getAppointmentComment(@PathVariable Long appointmentId) {
        String comment = appointmentService.findCommentByAppointmentId(appointmentId);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }
    @PutMapping("/{appointmentId}/comment")
    public ResponseEntity<Void> updateAppointmentComment(@PathVariable Long appointmentId, @RequestBody Map<String, String> body) {
        String comment = body.get("comment");
        boolean updated = appointmentService.updateAppointmentComment(appointmentId, comment);
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
