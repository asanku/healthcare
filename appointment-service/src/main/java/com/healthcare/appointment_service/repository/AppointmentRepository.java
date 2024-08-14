package com.healthcare.appointment_service.repository;

import com.healthcare.appointment_service.domain.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    List<Appointment> findAllByPatientId(String patientId);
    List<Appointment> findAllByDoctorId(String doctorId);
    List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId);
}
