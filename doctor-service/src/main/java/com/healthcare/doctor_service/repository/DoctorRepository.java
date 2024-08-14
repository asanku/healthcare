package com.healthcare.doctor_service.repository;

import com.healthcare.doctor_service.domain.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findAllByOpDaysContains(String[] days);
}
