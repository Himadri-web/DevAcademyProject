package com.poc.patient.exception;
/**
 * @author HS106406
 * This PatientDetailsNotFoundException class used for user defined exception when patient details not exist
 */
public class PatientDetailsNotFoundException extends RuntimeException{

    public PatientDetailsNotFoundException(String message) {
        super(message);
    }
}
