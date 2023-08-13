package com.koliche.demo_hospital.security.services;

import com.koliche.demo_hospital.security.entities.AppRole;
import com.koliche.demo_hospital.security.entities.AppUser;
import com.koliche.demo_hospital.security.repo.AppRoleRepository;
import com.koliche.demo_hospital.security.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser user = appUserRepository.findByUsername(username);
        if(user!=null)throw new RuntimeException("this user already exists!");
        if(!password.equals(confirmPassword))throw new RuntimeException("password not match!");
        AppUser appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedUser = appUserRepository.save(appUser);
        return savedUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if(appRole!=null)throw new RuntimeException("this role already exists!");
        appRole= AppRole.builder().role(role).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser= appUserRepository.findByUsername(username);
        AppRole appRole= appRoleRepository.findById(role).get();
        appUser.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser= appUserRepository.findByUsername(username);
        AppRole appRole= appRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username);
        return user;
    }
}
