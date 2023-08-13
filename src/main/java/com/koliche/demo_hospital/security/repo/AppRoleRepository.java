package com.koliche.demo_hospital.security.repo;

import com.koliche.demo_hospital.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}
