package com.poc.patient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HS106406
 * This Patient class represents as Entity and Model class for holding patient details
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    //@OneToMany(targetEntity = Address.class, mappedBy = "patient", cascade = CascadeType.PERSIST)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patientId_fk", referencedColumnName = "patientId")
    private List<Address> address = new ArrayList<>();

    @JsonProperty
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="patientId_fk", referencedColumnName = "patientId")
    private List<Telephone>  mobileNumber = new ArrayList<>();

}
