package com.Methods.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Methods.Entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
