package com.danarg.pncontrollerseccion01.repositories;

import com.danarg.pncontrollerseccion01.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByNameIn(List<String> names);

}
