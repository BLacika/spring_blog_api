package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public Category categoryById(@PathVariable Long id) {
        return categoryService.findOne(id);
    }
}
