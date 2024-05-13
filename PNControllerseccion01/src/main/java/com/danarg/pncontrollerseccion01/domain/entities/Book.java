package com.danarg.pncontrollerseccion01.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Sec01_books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID code;
    private String ISBN;
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
