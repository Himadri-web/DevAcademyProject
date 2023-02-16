package com.poc.patient.service;

import com.poc.patient.exception.PatientDetailsNotFoundException;
import com.poc.patient.model.Address;
import com.poc.patient.model.Patient;
import com.poc.patient.model.Telephone;
import com.poc.patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PatientServicesImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServicesImpl testClass;

    private static Patient patient;

    @BeforeAll
    static void init(){
        Address permanentAddress = new Address();
        permanentAddress.setAddressId(556);
        permanentAddress.setAddressType("Permanent Address");
        permanentAddress.setStreet("Jajpur");
        permanentAddress.setCity("Bhubaneswar");
        permanentAddress.setState("Odisha");
        permanentAddress.setPostalCode("755014");

        Telephone alternateContact = new Telephone();
        alternateContact.setPhoneId(334);
        alternateContact.setPhoneNumber("Alternate Contact");
        alternateContact.setCountryCode("+91");
        alternateContact.setPhoneNumber("9884473XXX");

        patient = new Patient();
        patient.setPatientId(1);
        patient.setPatientName("Himadri");
        patient.setGender('M');
        patient.setMobileNumber(
                Arrays.asList(Telephone.builder().
                                phoneId(333).
                                phoneType("Primary Contact").
                                countryCode("+91").
                                phoneNumber("9884473347")
                                .build(),
                        alternateContact
                )
        );
        patient.setDateOfBirth("06/06/1985");
        patient.setAddress(Arrays.asList(Address.builder()
                        .addressId(555)
                        .addressType("Present Address")
                        .street("MCN Nagar Extension")
                        .city("Chennai")
                        .state("Tamil Nadu")
                        .postalCode("600097")
                        .build(),
                permanentAddress

        ));
       // patient.setAddress("Thoraipakkam, Chennai");
    }

    @Test
    void addPatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        Patient actual = testClass.addPatient(patient);
        assertEquals(patient.getPatientName(),actual.getPatientName());
    }

    @Test
    void getPatientDetails() {
        List<Patient> patients = Arrays.asList(patient);
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> actual = testClass.getPatientDetails();

        assertEquals(patients.size(), actual.size());
        assertDoesNotThrow(() -> testClass.getPatientDetails());
        verify(patientRepository, times(2)).findAll();
    }
    @Test
    void getPatientsByName() {
        List<Patient> patients = Arrays.asList(patient);
        when(patientRepository.findByPatientNameContainingIgnoreCase(anyString())).thenReturn(patients);
        List<Patient> actual = testClass.getPatientsByName("Himadri");
        assertEquals(patients.size(), actual.size());
        assertNotNull(actual);
    }

    @Test
    void testGetPatientDetailsForPatientDetailsNotFoundException() {
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(PatientDetailsNotFoundException.class, () -> testClass.getPatientDetails());
        String expected = "There is no Patient record exist in DB.";
        try {
            testClass.getPatientDetails();
            fail("Test expecting PatientDetailsNotFoundException");
        }catch(PatientDetailsNotFoundException pdnfe){
            assertEquals(expected, pdnfe.getMessage());
        }
    }

    @Test
    void removePatientById() {
        when(patientRepository.existsById(eq(1))).thenReturn(true);
        String statusMessage = testClass.removePatientById(1);
        assertEquals("Patient removed successfully for patient id = 1", statusMessage);
        verify(patientRepository, times(1)).existsById(anyInt());
        verify(patientRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void testRemovePatientByIdForPatientDetailsNotFoundException() {
        when(patientRepository.existsById(eq(1))).thenReturn(false);
        assertThrows(PatientDetailsNotFoundException.class, () -> testClass.removePatientById(1));
        String expected = "Patient not found in db with given patient id = 1";
        try {
            testClass.removePatientById(1);
            fail("Test expecting PatientDetailsNotFoundException");
        }catch(PatientDetailsNotFoundException pdnfe){
            assertEquals(expected, pdnfe.getMessage());
        }

    }

    @Test
    void updatePatientById() {
        Optional<Patient> optionalPatient = Optional.ofNullable(patient);
        when(patientRepository.findById(eq(1))).thenReturn(optionalPatient);

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient actual = testClass.updatePatientById(1, patient);

        assertEquals(patient.getPatientName(), actual.getPatientName());
        assertEquals(1, actual.getPatientId());

        verify(patientRepository, times(1)).findById(anyInt());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void testUpdatePatientByIdForPatientDetailsNotFoundException() {

        when(patientRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(PatientDetailsNotFoundException.class, () -> testClass.updatePatientById(1, patient));

        String expected = "No Patient exist with Patient Id = 1";
        try{
            testClass.updatePatientById(1, patient);
            fail("Test expecting AccountDetailsNotFoundException");
        } catch (PatientDetailsNotFoundException pdnfe) {
            assertEquals(expected, pdnfe.getMessage());
        }
    }

    @Test
    void getPatientDetailById() {
        Optional<Patient> optionalPatient = Optional.ofNullable(patient);
        when(patientRepository.findById(eq(1))).thenReturn(optionalPatient);
        Patient actual = testClass.getPatientDetailById(1);

        assertEquals(patient.getPatientName(), actual.getPatientName());

        verify(patientRepository, times(1)).findById(eq(1));
    }
    @Test
    void testGetPatientDetailByIdForPatientDetailsNotFoundException() {
        when(patientRepository.findById(eq(1))).thenReturn(Optional.empty());
        assertThrows(PatientDetailsNotFoundException.class, () -> testClass.getPatientDetailById(1));
        String expected = "No Patient exist with Patient Id = 1";
        try {
            testClass.getPatientDetailById(1);
            fail("Test expecting PatientDetailsNotFoundException");
        }catch(PatientDetailsNotFoundException pdnfe){
            assertEquals(expected, pdnfe.getMessage());
        }
        verify(patientRepository, times(2)).findById(eq(1));
    }
}