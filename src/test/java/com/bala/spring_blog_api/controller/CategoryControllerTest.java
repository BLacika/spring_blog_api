package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryController categoryController;

    @Test
    void categories() throws Exception {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now())
        );

        Mockito.when(categoryController.categories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].title", is(categories.get(0).getTitle())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void categoryById() throws Exception {
        Category category = new Category(1L, "Teszt",
                LocalDateTime.of(2020, 7, 11, 16, 50),
                LocalDateTime.of(2020, 7, 11, 16, 50));

        Mockito.when(categoryController.categoryById(category.getId()))
                .thenReturn(ResponseEntity.ok(category));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Teszt")))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void create() throws Exception{
        Category newCategory = new Category(1L, "New Category", LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(categoryController.create(newCategory)).thenReturn(ResponseEntity.ok(newCategory));

        ResponseEntity<Category> responseEntity = categoryController.create(newCategory);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void update() {
        Category updateCategory = new Category(1L, "Updated", LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(categoryController.update(updateCategory.getId(), updateCategory)).thenReturn(ResponseEntity.ok(updateCategory));

        ResponseEntity<Category> responseEntity = categoryController.update(updateCategory.getId(), updateCategory);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(updateCategory, responseEntity.getBody());
    }

    @Test
    void delete() {
        Category deleteCategory = new Category(1L, "Deleting", LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(categoryController.delete(deleteCategory.getId())).thenReturn(ResponseEntity.ok(Mockito.anyString()));

        ResponseEntity response = ResponseEntity.ok(categoryController.delete(deleteCategory.getId()));

        assertNotNull(response);
        assertNotNull(response.getBody());
    }
}