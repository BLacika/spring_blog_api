package com.bala.spring_blog_api.repository;

import com.bala.spring_blog_api.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void whenFindAll() {
        List<Category> categories = categoryRepository.findAll();
        assertNotNull(categories);
        assertTrue(!categories.isEmpty());
    }

    @Test
    public void whenFindById() {
        Category category = new Category(1L, "New", LocalDateTime.now(), LocalDateTime.now());
        Category saved = categoryRepository.save(category);
        Category find = categoryRepository.findById(saved.getId()).get();
        assertNotNull(find);
    }

    @Test
    public void whenCreate() {
        Category category = new Category(1L, "New", LocalDateTime.now(), LocalDateTime.now());
        Category saved = categoryRepository.save(category);
        Category newCategory = categoryRepository.findById(saved.getId()).get();
        assertNotNull(newCategory);
        assertEquals("New", newCategory.getTitle());
        assertEquals(category.getId(), newCategory.getId());
    }

    @Test
    public void whenDelete() {
        Category category = new Category(1L, "New", LocalDateTime.now(), LocalDateTime.now());
        Category saved = categoryRepository.save(category);
        assertNotNull(categoryRepository.findById(saved.getId()).get());

        categoryRepository.deleteById(category.getId());
        assertThat(categoryRepository.findAll()).doesNotContain(category);
    }

}