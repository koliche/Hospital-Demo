package com.koliche.demo_hospital;

import com.koliche.demo_hospital.entities.Patient;
import com.koliche.demo_hospital.repository.PatientRepository;
import com.koliche.demo_hospital.security.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class DemoHospitalApplication implements CommandLineRunner {
//    private final PatientRepository patientRepository;
//
//    public DemoHospitalApplication(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(DemoHospitalApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
//        patientRepository.save(new Patient(null, "mohamed", new Date(), false, 100));
//        patientRepository.save(new Patient(null, "ahmed", new Date(), false, 1100));
//        patientRepository.save(new Patient(null, "nabil", new Date(), true, 13200));
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("User");
            accountService.addNewRole("Admin");
            accountService.addNewUser("user1", "123","test1@gmail.com","123");
            accountService.addNewUser("user2", "123","test2@gmail.com","123");
            accountService.addNewUser("admin", "123","test3@gmail.com","123");

            accountService.addRoleToUser("user1","User");
            accountService.addRoleToUser("user2","User");
            accountService.addRoleToUser("admin","User");
            accountService.addRoleToUser("admin","Admin");

        };

    }
}
