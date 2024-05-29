package com.danarg.pncontrollerseccion01.repositories;

import com.danarg.pncontrollerseccion01.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameOrEmail(String username, String Email);

}
