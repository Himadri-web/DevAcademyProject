package com.poc.patient.service;

import com.poc.patient.model.Patient;

import java.util.List;

/**
 * @author HS106406
 * This Interface provides all the service layer functionality
 */
public interface PatientService {
    /**
     * This is to add a new patient into system
     * @param patient It takes Patient details
     * @return It returns patient details after persisting into DB
     */
    Patient addPatient(Patient patient);

    /**
     * GET /patient : read patients data
     * This operation provides a view of all the patients data in JSON.
     * @return successful. Response Body contains the list of patients. (status code 200)
     *         or There is no patients present in db. (status code 404)
     */
    List<Patient> getPatientDetails();

    /**
     * This to remove a patient from database based on patient Id
     * @param patientId It takes Patient Id as argument
     * @return This returns successful message if the patient removed successfully from DB
     */
    String removePatientById(Integer patientId);

    /**
     * This to update patient details for a patient Id
     * @param patientId : ID of the patient
     * @param patient : Patient details to be updated
     * @return Success: Body contains the message of success (status code 200)
     *         Failure: "Patient not found in db with given patient id = ?
     */
    Patient updatePatientById(Integer patientId, Patient patient);

    /**
     * This to fetch patient details based on patient Id
     * @param patientId ID of the patient
     * @return Success. Body contains the given id patient details. (status code 200)
     *         or Patient Not Found for given ID. (status code 404)
     *         or Internal Server Error (status code 500)
     */
    Patient getPatientDetailById(Integer patientId);
}
