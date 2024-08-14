package com.healthcare.patient_service.converters;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.domain.PreExistingIllness;
import com.healthcare.patient_service.dto.PatientDto;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class PatientDtoConverter {

    public PatientDto toDto(Patient patient) {
        return new PatientDto(patient.getId(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getPhone(),
                patient.getDob(),
//                patient.getPreExistingIllnesses() != null ? patient.getPreExistingIllnesses().stream().map(PreExistingIllness::getIllness).toList():null);
                    patient.getPreExistingIllnesses() != null ? patient.getPreExistingIllnesses().stream().map(PreExistingIllness::getIllness).toList() : null);

    }

    public Patient toEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setFullName(dto.fullName());
        patient.setEmail(dto.email());
        patient.setAddress(dto.address());
        patient.setPhone(dto.phone());
        patient.setDob(dto.dob());
        if (!dto.preExistingIllness().isEmpty()){
            patient.setPreExistingIllnesses(dto.preExistingIllness().stream().map(PreExistingIllness::new).toList());
        }
        return patient;
    }

}
