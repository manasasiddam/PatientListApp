package com.Methods.service;

import java.util.List;
import java.util.Map;

import com.Methods.Entity.Patient;
import com.Methods.exceptionHandling.PatientException;

public interface PatientService {

	public String addPatient(Patient patient);

	public Patient getPatientById(Integer id);

	List<Patient> getAllPatients(Long length);
	
	Patient updatePatient(Patient patient, Integer id);
	Patient updatePatientByFields(Map<String, Object> fields,Integer id);

	public boolean deletePatient(Integer id) throws PatientException;


	
}