package com.danarg.pncontrollerseccion01.domain.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "Sec01_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
}
