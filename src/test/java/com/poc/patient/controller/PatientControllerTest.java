package com.poc.patient.controller;

import com.poc.patient.model.Address;
import com.poc.patient.model.Patient;
import com.poc.patient.model.Telephone;
import com.poc.patient.service.PatientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    private static Patient patient;

    @BeforeAll
    static void init(){
        patient = Patient.builder()
                .patientId(100)
                .patientName("Himadri")
                .dateOfBirth("06/06/1985")
                .gender('M')
                .mobileNumber(Arrays.asList(Telephone.builder().
                                phoneId(333).
                                phoneType("Primary Contact").
                                countryCode("+91").
                                phoneNumber("9884473347")
                                .build(),
                        Telephone.builder().
                                phoneId(334)
                                .phoneType("Alternate Contact")
                                .countryCode("+91")
                                .phoneNumber("9884473000")
                                .build()))
                .address(Arrays.asList(Address.builder()
                                .addressId(555)
                                .addressType("Present Address")
                                .street("MCN Nagar Extension")
                                .city("Chennai")
                                .state("Tamil Nadu")
                                .postalCode("600097")
                                .build(),
                                Address.builder()
                                        .addressId(556)
                                        .addressType("Permanent Address")
                                        .street("Jajpur")
                                        .city("Bhubaneswar")
                                        .state("Odisha")
                                        .postalCode("755014")
                                        .build()
                )
                )
                .build();
    }

    @Test
    void addPatient() {
        when(patientService.addPatient(any(Patient.class))).thenReturn(patient);
        ResponseEntity<Patient> actual = patientController.addPatient(patient);
        assertEquals(patient.getPatientName(), actual.getBody().getPatientName());
        assertEquals(patient.getAddress(), actual.getBody().getAddress());
        assertEquals(patient.getGender(), actual.getBody().getGender());
        assertEquals(patient.getMobileNumber(), actual.getBody().getMobileNumber());
        assertEquals(patient.getDateOfBirth(), actual.getBody().getDateOfBirth());
        assertEquals(patient.getPatientId(), actual.getBody().getPatientId());
        assertEquals(patient.getAddress().size(), actual.getBody().getAddress().size());
        assertEquals(patient.getAddress().get(0).getAddressId(), actual.getBody().getAddress().get(0).getAddressId());
        assertEquals(patient.getAddress().get(0).getAddressType(), actual.getBody().getAddress().get(0).getAddressType());
        assertEquals(patient.getAddress().get(0).getStreet(), actual.getBody().getAddress().get(0).getStreet());
        assertEquals(patient.getAddress().get(0).getCity(), actual.getBody().getAddress().get(0).getCity());
        assertEquals(patient.getAddress().get(0).getState(), actual.getBody().getAddress().get(0).getState());
        assertEquals(patient.getAddress().get(0).getPostalCode(), actual.getBody().getAddress().get(0).getPostalCode());
        assertEquals(patient.getMobileNumber().size(), actual.getBody().getMobileNumber().size());
        assertEquals(patient.getMobileNumber().get(0).getPhoneId(), actual.getBody().getMobileNumber().get(0).getPhoneId());
        assertEquals(patient.getMobileNumber().get(0).getPhoneType(), actual.getBody().getMobileNumber().get(0).getPhoneType());
        assertEquals(patient.getMobileNumber().get(0).getPhoneNumber(), actual.getBody().getMobileNumber().get(0).getPhoneNumber());
        assertEquals(patient.getMobileNumber().get(0).getCountryCode(), actual.getBody().getMobileNumber().get(0).getCountryCode());

    }

    @Test
    void getPatientDetails() {
        List<Patient> patients = Arrays.asList(patient);
        when(patientService.getPatientDetails()).thenReturn(patients);
        ResponseEntity<List<Patient>> actual = patientController.getPatientDetails();
        assertEquals(patients.size(), actual.getBody().size());
        assertNotNull(actual);
        assertEquals(HttpStatus.OK.value(), actual.getStatusCodeValue());
    }

    @Test
    void getPatientDetailById() {
        when(patientService.getPatientDetailById(eq(100))).thenReturn(patient);
        ResponseEntity<Patient> actual = patientController.getPatientDetailById(100);
        assertNotNull(actual);
        assertEquals(patient.getPatientName(), actual.getBody().getPatientName());
        assertEquals(patient.getGender(), actual.getBody().getGender());
        assertEquals(patient.getMobileNumber(), actual.getBody().getMobileNumber());
    }

    @Test
    void updatePatient() {
        when(patientService.updatePatientById(eq(100), any(Patient.class))).thenReturn(patient);
        ResponseEntity<Patient> actual = patientController.updatePatient(100, patient);
        assertEquals(patient.getPatientName(), actual.getBody().getPatientName());
        assertEquals(HttpStatus.ACCEPTED.value(), actual.getStatusCodeValue());
    }

    @Test
    void removePatientById() {
        ResponseEntity<String> actual = patientController.removePatientById(100);

        assertEquals(HttpStatus.OK.value(), actual.getStatusCodeValue());

        verify(patientService, times(1)).removePatientById(anyInt());
    }
}