package com.healthcare.patient_service.repository;

import com.healthcare.patient_service.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {


    @Query(name = "Patient.findByPhone")
    Optional<Patient> findByPhone(@Param("phone") String Phone);

    @Query(name = "Patient.findByEmail")
    Optional<Patient> findByEmail(@Param("email") String Email);

    @Query(name = "Patient.findByEmailOrPhone")
    Optional<Patient> findByEmailOrPhone(@Param("email") String email, @Param("phone") String Phone);

    @Query(name = "Patient.findByDobBetween")
    List<Patient> findByDobBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //@Query("select p.illness from patient p where p.id= :pid")
    //Optional<List<Patient>> findByIllnessOfPatient(@Param("patientId") Integer pid);

    @Query("select p.preExistingIllnesses from Patient p where p.id= :patientId")
    Optional<List<Patient>> findByIllnessOfPatient(@Param("patientId") Integer patientid);

}
//select patient_id from patient, nativequery=true