package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> categoryById(@PathVariable Long id) {
        Category category = categoryService.findOne(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/categories/create")
    public ResponseEntity<Category> create(@Valid @RequestBody Category newCategory) {
        Category category = categoryService.createCategory(newCategory);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(id, updatedCategory);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
