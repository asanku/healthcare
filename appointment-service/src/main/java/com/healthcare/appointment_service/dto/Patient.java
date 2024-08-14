package com.healthcare.appointment_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Patient(String id, String fullName, String phone) {
}
