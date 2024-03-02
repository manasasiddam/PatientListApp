package com.Methods.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Methods.Entity.Patient;
import com.Methods.exceptionHandling.PatientException;
import com.Methods.service.PatientService;

@RestController

@RequestMapping("/Patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> savePatientToDB(@RequestBody Patient patient){
			String addPatient = patientService.addPatient(patient);
		return new ResponseEntity<>(addPatient,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public	ResponseEntity<Patient>getPatient(@PathVariable Integer id){
		Patient patientById = patientService.getPatientById(id);
		return new ResponseEntity<>(patientById,HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatients(@RequestParam(name = "length",required = false) Long length) {
	    List<Patient> allPatients = patientService.getAllPatients(length);
	    
	    if (length == null) {
	    return new ResponseEntity<>(allPatients.stream().limit(allPatients.size()).collect(Collectors.toList()), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(allPatients.stream().limit(length).collect(Collectors.toList()), HttpStatus.OK);
	    }
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Patient>updatePatient(@RequestBody Patient patient,@PathVariable Integer id){
		Patient updatedPatient = patientService.updatePatient(patient, id);
		return new ResponseEntity<>(updatedPatient,HttpStatus.ACCEPTED);
		
	}
	
	
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deletePatient(@PathVariable Integer id)  {
	        boolean isDeleted1 = patientService.deletePatient(id);
	        String msg="";
	        if(isDeleted1) {
	        	msg= "Patient Deleted successfully";
	        }
	        	
	        return new ResponseEntity<>(msg, HttpStatus.OK);
	    }
	
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<Patient>updatPatientByFields(@RequestBody Map<String, Object> fields,@PathVariable Integer id){
		Patient updatePatientByFields = patientService.updatePatientByFields(fields, id);
		return new ResponseEntity<>(updatePatientByFields,HttpStatus.ACCEPTED);
		
	}

}
