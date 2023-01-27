package com.poc.patient.service;

import com.poc.patient.model.Patient;
import com.poc.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.ParameterExpression;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientServicesImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Override
    public Patient addPatient(Patient patient) {
        Patient p = patientRepository.save(patient);
        return p;
    }

    @Override
    public List<Patient> getPatientDetails() {
        List<Patient> patients = patientRepository.findAll();
        log.info("Number of records found: {}", patients.size());
        if(patients.isEmpty()){
            log.info("No record found");
        }
        return patients;
    }

    @Override
    public String removePatient(Integer patientId) {
        log.info("removePatient of PatientService");
        Boolean isPatientExist = patientRepository.existsById(patientId);
        if(isPatientExist){
           patientRepository.deleteById(patientId);
        }else{
            log.error("patientId : {} is does not exist to delete");
        }
        return isPatientExist ? "PatientId deleted successfully" : " Failed to remove patientId";
    }

    @Override
    public Patient updatePatient(Integer patientId, Patient patient) {
        Optional<Patient> patientFromRepo = patientRepository.findById(patientId);
        if(!patientFromRepo.isPresent()){
            try {
                throw new Exception("Patient not found in db with given patient id = " + patientId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        patient.setPatientId(patientId);
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientDetailById(Integer patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        return patient.get();
    }


}
