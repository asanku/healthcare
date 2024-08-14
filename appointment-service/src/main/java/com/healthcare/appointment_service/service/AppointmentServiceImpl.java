package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.domain.Appointment;
import com.healthcare.appointment_service.dto.Doctor;
import com.healthcare.appointment_service.dto.Patient;
import com.healthcare.appointment_service.feign_clients.DoctorServiceClient;
import com.healthcare.appointment_service.feign_clients.PatientServiceClient;
import com.healthcare.appointment_service.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorServiceClient doctorServiceClient;
    private final PatientServiceClient patientServiceClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorServiceClient doctorServiceClient, PatientServiceClient patientServiceClient) {
        this.appointmentRepository = appointmentRepository;
        this.doctorServiceClient = doctorServiceClient;
        this.patientServiceClient = patientServiceClient;
    }

    public Appointment createAppointment(Appointment appointment) {
        //return
        appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> findAllByPatientId(String patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    public List<Appointment> findAllByDoctorId(String doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    public List<Appointment> findAllByPatientIdAndDoctorId(String doctorId, String patientId) {
        return appointmentRepository.findAllByPatientIdAndDoctorId(doctorId,patientId);
    }

    public Patient getPatientById(String patientId) {
        return patientServiceClient.getPatientById(patientId);
    }

    public Doctor getDoctorById(String doctorId) {
        return doctorServiceClient.getDoctorById(doctorId);
    }
}
