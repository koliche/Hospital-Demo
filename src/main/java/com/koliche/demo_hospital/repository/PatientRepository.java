package com.koliche.demo_hospital.repository;

import com.koliche.demo_hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

//    public Patient getByNameContaining(String val){}

}
