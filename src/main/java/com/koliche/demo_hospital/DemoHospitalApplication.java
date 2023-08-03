package com.koliche.demo_hospital;

import com.koliche.demo_hospital.entities.Patient;
import com.koliche.demo_hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DemoHospitalApplication implements CommandLineRunner {
    private final PatientRepository patientRepository;

    public DemoHospitalApplication(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoHospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        patientRepository.save(new Patient(null, "mohamed", new Date(), false, 100));
//        patientRepository.save(new Patient(null, "ahmed", new Date(), false, 1100));
//        patientRepository.save(new Patient(null, "nabil", new Date(), true, 13200));

    }
}
