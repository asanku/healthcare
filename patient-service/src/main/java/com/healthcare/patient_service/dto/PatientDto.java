package com.healthcare.patient_service.dto;

import com.healthcare.patient_service.domain.PreExistingIllness;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record PatientDto(

        int id,

        @NotEmpty(message = "full name required")
        @Length(min=2,message = "length must be at least 2 char length")
        @Pattern(regexp = "^[a-zA-Z\\s]+$",message = "name invalid")
        String fullName,

        @NotEmpty(message = "email required")
       // @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        @Email(message = "email invalid")
        String email,

        @NotEmpty(message = "address required")
        String address,

        @NotEmpty(message = "phn no required")
        @Pattern(regexp = "(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "phn no invalid")
        String phone,

        //@NotEmpty(message = "dob required")
        @Past(message = "dob must be from past")
        LocalDate dob,

        List<String> preExistingIllness



) {
}



