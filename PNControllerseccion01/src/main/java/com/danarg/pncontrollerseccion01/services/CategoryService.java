package com.danarg.pncontrollerseccion01.services;

import com.danarg.pncontrollerseccion01.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);

    List<Category> findAllCategories();
    Category findCategoryByCode(String code);

    void deleteByName(String name);



}
