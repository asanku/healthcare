package com.healthcare.appointment_service.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "appointment")
public class Appointment {

    @Id
    private String id;

    @Field("appointment_date")
    private String appointmentDate;

    @Field("appointment_time")
    private String appointmentTime;

    @Field("patient_id")
    private String patientId;

    @Field("patient_name")
    private String patientName;

    @Field("patient_number")
    private String patientNumber;

    @Field("doctor_id")
    private String doctorId;

    @Field("doctor_name")
    private String doctorName;


    private String status;

    public Appointment(String appointmentDate, String appointmentTime, String patientId, String patientName, String patientNumber, String doctorId, String doctorName, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientNumber= patientNumber;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.status = status;

    }

    public void toDto(){

    }
}



/*
 * {
 *     "id": 1,
 *     appointmentDate: "2021-09-01",
 *     appointmentTime: "10:00 AM",
 *     patientId: 1,
 *     doctorId: 1,
 *     status: "CONFIRMED"
 * }
 */