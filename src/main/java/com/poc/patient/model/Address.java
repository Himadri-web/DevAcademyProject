package com.poc.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
/**
 * @author HS106406
 * This Address class represents as Entity and Model class for holding Address details of a patient
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    private String addressType;
    private String street;
    private String city;
    private String state;
    private String postalCode;


}
