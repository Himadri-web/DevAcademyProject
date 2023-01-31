package com.poc.patient.service;

import com.poc.patient.model.Patient;

import java.util.List;

/**
 * @author HS106406
 * This Interface provides all the service layer functionality
 */
public interface PatientService {
    Patient addPatient(Patient patient);

    /**
     * GET /patient : reads patients data
     * This operation provides a view of all the patients data in JSON.
     * @return successful. Response Body contains the list of accounts. (status code 200)
     *         or There is no patients present in db. (status code 404)
     */
    List<Patient> getPatientDetails();

    String removePatientById(Integer patientId);

    /**
     * @param patientId : ID of the patient
     * @param patient : Patient details to be updated
     * @return Success: Body contains the message of success (status code 200)
     *         Failure: "Patient not found in db with given patient id = ?
     */
    Patient updatePatientById(Integer patientId, Patient patient);

    /**
     * @param patientId ID of the patient
     * @return Success. Body contains the given id patient details. (status code 200)
     *         or Patient Not Found for given ID. (status code 404)
     *         or Internal Server Error (status code 500)
     */
    Patient getPatientDetailById(Integer patientId);
}
