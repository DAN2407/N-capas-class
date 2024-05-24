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
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByCode(String code) {
        return categoryRepository.findById(code).orElse(null);
    }

    @Override
    public void deleteByName(String name) {
        categoryRepository.deleteByName(name);
    }
}
