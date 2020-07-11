package com.bala.spring_blog_api.service;

import com.bala.spring_blog_api.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void findAll() {
        List<Category> categories = categoryService.findAll();
        assertNotNull(categories);
        assertTrue(!categories.isEmpty());
    }

    @Test
    void findOne() {
        Category category = categoryService.findOne(1L);
        assertNotNull(category);
    }

    @Test
    void createCategory() {
        Category newCategory = new Category(1L, "New", LocalDateTime.now(), LocalDateTime.now());
        Category saved = categoryService.createCategory(newCategory);
        Category find = categoryService.findOne(saved.getId());
        assertNotNull(find);
        assertEquals(newCategory.getTitle(), find.getTitle());
    }
}