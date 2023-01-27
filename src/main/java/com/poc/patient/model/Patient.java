package com.poc.patient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer patientId;

    @JsonProperty
    private String patientName;

    @JsonProperty
    @Column(name="DateOfBirth")
    private String dateOfBirth;

    @JsonProperty
    private Character gender;

    @JsonProperty
    private String address;

    @JsonProperty
    private String mobileNumber;

}
