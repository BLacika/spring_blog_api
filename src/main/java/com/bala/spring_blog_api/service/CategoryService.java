package com.bala.spring_blog_api.service;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
    }

    // Create Category
    public Category createCategory(Category newCategory) {
        newCategory.setCreatedAt(LocalDateTime.now());
        newCategory.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(newCategory);
    }

    // Update Category
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setTitle(updatedCategory.getTitle());
                    category.setUpdatedAt(LocalDateTime.now());
                    return categoryRepository.save(category);
                }).get();
    }

    // Delete Category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
