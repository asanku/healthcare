package com.healthcare.patient_service.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pre_existing_illness")
public class PreExistingIllness {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "illness_name")
    private String illness;

    public PreExistingIllness(String illness) {
        this.illness = illness;
    }
}
