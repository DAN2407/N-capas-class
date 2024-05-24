package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    void save();

    List<Category> findAll();

    Category findByName(String name);

    void deleteByName(String name);



}
