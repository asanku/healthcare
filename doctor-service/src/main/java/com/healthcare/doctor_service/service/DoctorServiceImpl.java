package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.domain.Doctor;
import com.healthcare.doctor_service.dto.DoctorDto;
import com.healthcare.doctor_service.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
        /*Doctor doctor = Doctor.builder()
                .fullName(doctorDto.fullName())
                .specilization(doctorDto.specialization())
                .build();
        doctor = doctorRepository.save(doctor);
        return mapToDto(doctor);
         */
    }
    @Override
    public boolean checkIfDoctorExists(String doctorId){
        return doctorRepository.existsById(doctorId);
    }

    @Override
    public List<Doctor> getDoctorsByOpDays(String[] days) {
        return List.copyOf(doctorRepository.findAllByOpDaysContains(days));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return List.copyOf(doctorRepository.findAll());
    }

    public Optional<Doctor> getDoctor(String id) {
        return doctorRepository.findById(id);
    }

    /*
    public DoctorDto getDoctorById(int id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Doctor not found"));
        return mapToDto(doctor);
    }

    public List<DoctorDto> getAllDoctors(){
        return doctorRepository.findAll().stream().map(doctor -> mapToDto(doctor))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto updateDoctor(int id, DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Doctor not found"));
        doctor.setFullName(doctorDto.fullName());
        doctor.setSpecilization(doctorDto.specialization());
        doctor = doctorRepository.save(doctor);
        return mapToDto(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Doctor not found"));
        doctorRepository.delete(doctor);
    }

    private DoctorDto mapToDto(Doctor doctor)
    {
        return new DoctorDto(doctor.getId(),doctor.getFullName(),doctor.getSpecilization());
    }
           */
}
