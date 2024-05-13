package com.danarg.pncontrollerseccion01.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Sec01_categories")
public class Category {
    @Id
//    @Column(name = "category_code")
    private String code;
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Book> books;
}
