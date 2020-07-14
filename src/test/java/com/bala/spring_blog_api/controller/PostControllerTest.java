package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.model.Post;
import com.bala.spring_blog_api.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;

    @Test
    void posts() throws Exception {
        when(postService.getPosts()).thenReturn(Arrays.asList(
                new Post(1L,
                        "Post1",
                        "Author1",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Content1",
                        "published",
                        "Tags",
                        new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()))
        ));
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Post1")));
    }

    @Test
    void postById() throws Exception {
        when(postService.getPostById(anyLong())).thenReturn(
                new Post(1L,
                        "Post1",
                        "Author1",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Content1",
                        "published",
                        "Tags",
                        new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()))
        );
        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Post1")))
                .andExpect(jsonPath("$.content", is("Content1")));
    }
}