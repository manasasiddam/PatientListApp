package com.Methods.service;

import java.awt.RenderingHints.Key;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.Methods.Entity.Patient;
import com.Methods.exceptionHandling.PatientException;
import com.Methods.repository.PatientRepository;

import lombok.Value;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patientRepo;

	@Override
	public String addPatient(Patient patient) {

			Patient patientObj = patientRepo.save(patient);
			
			if(patientObj.getId()!=null) {
				return "Patient created sucessfully";
			}else {
			return "Patient not created";
	}
	}
	@Override
	public Patient getPatientById(Integer id) {
		
		return patientRepo.findById(id).orElseThrow(()->new PatientException("Patient cannot be found"));
	} 

	@Override
	public List<Patient> getAllPatients (Long length) {
		
		return patientRepo.findAll();
	}
	@Override
	public Patient updatePatient(Patient patient, Integer id) {
		Optional<Patient> findById = patientRepo.findById(id);
		
		if(findById.isPresent()) {
			Patient existingPatient = findById.get();
			existingPatient.setName(patient.getName());
			existingPatient.setGender(patient.getGender());
			existingPatient.setAge(patient.getAge());
			
			return patientRepo.save(existingPatient);	
		}
		
		return null;
	}
	@Override
	public Patient updatePatientByFields(Map<String, Object> fields, Integer id)  {
		Optional<Patient>existingPatient = patientRepo.findById(id);
		
		
		if(existingPatient.isPresent()) {
			fields.forEach((Key,value)->{
		Field field=ReflectionUtils.findRequiredField(Patient.class, Key);
		field.setAccessible(true);
		ReflectionUtils.setField(field, existingPatient.get(), value);
				
		});
			return patientRepo.save(existingPatient.get());
		}
		   return null;
		}
	@Override
	public boolean deletePatient(Integer id){
		boolean status=false;
		Patient patient = patientRepo.findById(id).orElseThrow(()->new PatientException("Patient record not fount") );
		
		if(patient!=null) {		
		patientRepo.deleteById(id);
		status=true;
		}

		return status;
		
	}
	
	}
