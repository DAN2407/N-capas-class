package com.danarg.pncontrollerseccion01.domain.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "Sec01_users")
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    private String username;
    private String email;
    @Convert(converter = Encrypter.class)
    private String password;

    @Column(nullable = false)
    private boolean enabled;
}
