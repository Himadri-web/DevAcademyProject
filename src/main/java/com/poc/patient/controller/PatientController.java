package com.poc.patient.controller;

import com.poc.patient.model.Patient;
import com.poc.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/api/patient")
    public ResponseEntity<List<Patient>> getPatientDetails(){
        log.info("Inside getPatientdetailes of PatientController");
        return new ResponseEntity<>(patientService.getPatientDetails(), HttpStatus.OK);
    }

    @GetMapping("/api/patient/{patientId}")
    public ResponseEntity<Patient> getPatientDetailById(@PathVariable Integer patientId){
        log.info("Inside getPatientDetailById of PatientController");
        return new ResponseEntity<>(patientService.getPatientDetailById(patientId), HttpStatus.OK);
    }

    @PostMapping("/api/patient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        log.info("Inside addPatient of PatientController");
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    @PutMapping("/api/patient/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer patientId, @RequestBody Patient patient){
        log.info("Inside updatePatient of PatientController");
        return new ResponseEntity<>(patientService.updatePatient(patientId, patient), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/patient/{patientId}")
    public ResponseEntity<String> removePatient(@PathVariable Integer patientId){
        log.info("removePatient of PatientController");
        return new ResponseEntity<>(patientService.removePatient(patientId), HttpStatus.OK);
    }
}
