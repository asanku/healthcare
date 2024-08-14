package com.healthcare.patient_service.service;


import com.healthcare.patient_service.domain.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient getPatient(int id);
    List<Patient> getAllPatients();
    Patient updatePatient(Patient patient);
    void deletePatient(int id);
    Patient searchByEmail(String email);
    Patient searchByPhone(String phone);


}
