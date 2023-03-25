package com.devmountain.appointmentScheduler.repositories;

import com.devmountain.appointmentScheduler.entities.Appointment;
import com.devmountain.appointmentScheduler.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByUserEquals(User user);

    List<Appointment> findByTrainerId(Long trainerId);

}
