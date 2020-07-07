package com.bala.spring_blog_api.service;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    // Get all Category
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Get one Category
    public Category findOne(Long id) {
        return categoryRepository.findById(id).get();
    }
}
