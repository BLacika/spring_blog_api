package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PostController postController;

    @Test
    void posts() throws Exception {
        when(postController.posts()).thenReturn(Arrays.asList(
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
        verify(postController, times(1)).posts();
    }

    @Test
    void postById() throws Exception {
        when(postController.postById(anyLong())).thenReturn(ResponseEntity.ok(
                new Post(1L,
                        "Post1",
                        "Author1",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Content1",
                        "published",
                        "Tags",
                        new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now())))
        );
        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Post1")))
                .andExpect(jsonPath("$.content", is("Content1")));
        verify(postController, times(1)).postById(1L);
    }

    @Test
    void createPost() throws Exception {
        Post post = new Post(1L,
                "Post1",
                "Author1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Content1",
                "published",
                "Tags",
                new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()));
        String content = objectMapper.writeValueAsString(post);

        when(postController.create(any(Post.class))).thenReturn(ResponseEntity.ok(post));

        mockMvc.perform(post("/api/posts/create")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(content)
            .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(content))
                .andReturn();
        verify(postController, times(1)).create(any(Post.class));
    }


    @Test
    void deletePost() throws Exception {
        when(postController.delete(any(Long.class))).thenReturn(ResponseEntity.ok(any(String.class)));
        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk());
        verify(postController, times(1)).delete(1L);
    }
}