package com.danarg.pncontrollerseccion01.services.impls;

import com.danarg.pncontrollerseccion01.domain.entities.Category;
import com.danarg.pncontrollerseccion01.repositories.CategoryRepository;
import com.danarg.pncontrollerseccion01.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save() {
        categoryRepository.save(new Category());
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    @Override
    public void deleteByName(String name) {
        categoryRepository.deleteByName(name);
    }


}
