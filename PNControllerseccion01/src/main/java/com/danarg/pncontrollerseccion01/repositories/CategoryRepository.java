package com.danarg.pncontrollerseccion01.repositories;

import com.danarg.pncontrollerseccion01.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Override
    Optional<Category> findById(String s);

    Optional<Category> findByName(String name);

    Optional<Category> deleteByName(String name);

    Optional<Category> save(String name);

    Optional<Category> findAll(String name);

    Optional<Category> findByCode(String code);

}
