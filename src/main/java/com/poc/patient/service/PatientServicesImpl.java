package com.poc.patient.service;

import com.poc.patient.exception.PatientDetailsNotFoundException;
import com.poc.patient.model.Patient;
import com.poc.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HS106406
 * This PatientServicesImpl class represents as a service Implemmentation layer
 */
@Slf4j
@Service
public class PatientServicesImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    /**
     *
     * @param patient It takes Patient details
     * @return It returns patient details after persisting into DB
     */
    @Override
    public Patient addPatient(Patient patient) {
        Patient p = patientRepository.save(patient);
        return p;
    }

    /**
     * @return This returns List of patient present in database
     */
    @Override
    public List<Patient> getPatientDetails() {
        List<Patient> patients = patientRepository.findAll();
        log.info("Number of records found: {}", patients.size());
        if(patients.isEmpty()){
            log.info("No record found");
            throw new PatientDetailsNotFoundException("There is no Patient record exist in DB.");
        }
        return patients;
    }

    /**
     * @param patientId It takes Patient Id as argument
     * @return This returns successful message if the patient removed successfully from DB
     */
    @Override
    public String removePatientById(Integer patientId) {
        log.info("removePatient of PatientService");
        Boolean isPatientExist = patientRepository.existsById(patientId);
        if(!isPatientExist){
            log.error("patientId : {} does not exist to delete", patientId);
            throw new PatientDetailsNotFoundException("Patient not found in db with given patient id = " + patientId);
        }
        patientRepository.deleteById(patientId);
        return "Patient removed successfully for patient id = " + patientId;
        //return isPatientExist ? "Patient removed successfully for patient id = " + patientId : " Failed to remove patient for patient Id = " + patientId;
    }

    /**
     * @param patientId It takes Patient Id as first parameter
     * @param patient It takes Patient details as second parameter
     * @return This returns patient details of the updated patient
     */
    @Override
    public Patient updatePatientById(Integer patientId, Patient patient) {
        Patient patientFromRepo = patientRepository.findById(patientId).orElseThrow(
                () -> new PatientDetailsNotFoundException("No Patient exist with Patient Id = " + patientId)
        );
        patient.setPatientId(patientId);
        return patientRepository.save(patient);
    }

    /**
     * @param patientId It takes Patient Id as path variable
     * @return This returns patient details of the shared patient Id
     */
    @Override
    public Patient getPatientDetailById(Integer patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new PatientDetailsNotFoundException("No Patient exist with Patient Id = " + patientId)
        );
        return patient;
    }
}
