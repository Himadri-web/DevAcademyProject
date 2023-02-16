package com.poc.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * @author HS106406
 * This Telephone class represents as Entity and Model class for holding contact details of a patient
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phoneId;
    private String phoneType;
    private String phoneNumber;
    private String countryCode;

}
