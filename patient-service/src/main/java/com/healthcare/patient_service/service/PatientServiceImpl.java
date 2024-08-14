package com.healthcare.patient_service.service;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.exceptions.DuplicatePatientException;
import com.healthcare.patient_service.exceptions.PatientNotFoundException;
import com.healthcare.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class PatientServiceImpl implements PatientService {

    //    Alternative to using lombok's @Slf4j annotation
    //    private Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        log.debug("Create patient {}", patient);
        patientRepository
                .findByEmailOrPhone(patient.getPhone(), patient.getEmail())
                .ifPresent(existingPatient -> {
                    log.error("Patient already exists with email:{} or phone: {}", existingPatient.getEmail() +" or phone: "+ existingPatient.getPhone());
                    throw new DuplicatePatientException("Patient already exists with email: "+ existingPatient.getEmail()+" or phone: "+existingPatient.getPhone());
                });
        log.debug("Patient does not exist with email: {} or phone: {}", patient.getEmail(), patient.getPhone());
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(int id) {
        log.debug("Get patient, id: {}", id);
        return patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient not found, id: "+id));
    }

    @Override
    public Patient updatePatient(Patient patient) {
        log.debug("updating patient: {}", patient);
        return patientRepository.save(patient);
    }

    public void deletePatient(int id){
        log.debug("Delete patient, id: {}", id);
        var patient = patientRepository.findById(id)
                        .orElseThrow(()->new PatientNotFoundException("Patient not found, cannot delete, id: "+ id));
        patientRepository.delete(patient);
    }

    @Override
    public Patient searchByEmail(String email) {
        log.debug("Search patient, email: {}", email);
        return patientRepository.findByEmail(email).orElseThrow(()->new PatientNotFoundException("Patient not found email: "+email));
    }

    public Patient searchByPhone(String phone) {
        log.debug("Search patient, phone: {}", phone);
        return patientRepository.findByPhone(phone).orElseThrow(()->new PatientNotFoundException("Patient not found phone: "+ phone));
    }

    public List<Patient> getAllPatients() {
        log.debug("Get all patients");
        return List.copyOf(patientRepository.findAll());
    }

    /*
    private PatientDto mapToDto(Patient patient) {
        return new PatientDto(patient.getId(), patient.getFullName(), patient.getEmail(), patient.getAddress(), patient.getPhone(), patient.getDateOfBirth());
    }
    */
}
