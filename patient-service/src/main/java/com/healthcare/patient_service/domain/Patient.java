package com.healthcare.patient_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "patients",
            uniqueConstraints = {@UniqueConstraint(columnNames = "email"),@UniqueConstraint(columnNames = "phone")})
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(name = "Patient.findByEmail",
                query = "select p from Patient p where p.email= :email"),
        @NamedQuery(name = "Patient.findByPhone",
                query = "select p from Patient p where p.phone= :phone"),
        @NamedQuery(name = "Patient.findByEmailOrPhone",
                query = "select p from Patient p where p.email= :email OR p.phone= :phone"),
        @NamedQuery(name = "Patient.findByDobBetween",
                query = "select p from Patient p where p.dob between :startDate and :endDate")
})

public class Patient implements Serializable {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "value")
    @SequenceGenerator(name = "value", initialValue = 100, allocationSize = 1)
    //@AssertTrue(message = "starts from 101")
    //boolean isValid(){
    //    return id>=101;
    //}

     */

    @GeneratedValue(strategy = GenerationType.AUTO)//, generator = "value")
    /*
    @TableGenerator(
            name = "value",
            table = "patients",         // Table name for ID generation
            pkColumnName = "id",            // Primary key column name
            valueColumnName = "id",        // Column that stores the current value
           // pkColumnValue = "your_entity_id",     // Primary key column value (name of the ID generator)
            initialValue = 50,                    // Starting value for the IDs
            allocationSize = 1                    // Increment size
    )

     */
    private int id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private LocalDate dob;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*
    @JoinTable(name = "patient_illness",
        joinColumns = @JoinColumn(name = "patient_id"),
//      inverseJoinColumns = @JoinColumn(name = "id"))
     */
    private List<PreExistingIllness> preExistingIllnesses;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
