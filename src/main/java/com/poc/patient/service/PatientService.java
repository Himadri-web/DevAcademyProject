package com.poc.patient.service;

import com.poc.patient.model.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient(Patient patient);

    List<Patient> getPatientDetails();

    String removePatient(Integer patientId);

    Patient updatePatient(Integer patientId, Patient patient);

    Patient getPatientDetailById(Integer patientId);
}
