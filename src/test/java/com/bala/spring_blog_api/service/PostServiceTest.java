package com.bala.spring_blog_api.service;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostServiceTest {

    @MockBean
    private PostService postService;

    @Test
    void getPosts() {
        Post post1 = new Post(1L,
                "Post1",
                "Author1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Content1",
                "published",
                "Tags",
                new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()));
        Post post2 = new Post(2L,
                "Post2",
                "Author2",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Content2",
                "published",
                "Tags",
                new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()));
        Post post3 = new Post(3L,
                "Post3",
                "Author3",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Content3",
                "published",
                "Tags",
                new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()));
        List<Post> posts = Arrays.asList(post1, post2, post3);

        when(postService.getPosts()).thenReturn(posts);

        List<Post> result = postService.getPosts();

        assertEquals(posts, result);
        assertNotNull(result);
        assertEquals(3, posts.size());
        assertThat(postService.getPosts()).hasSize(3);
        assertThat(postService.getPosts().get(0).getTitle()).isEqualTo("Post1");
        verify(postService, times(3)).getPosts();
    }

    @Test
    void getPostById() {
        Post post = new Post(1L,
                "Post1",
                "Author1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Content1",
                "published",
                "Tags",
                new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()));
        when(postService.getPostById(1L)).thenReturn(post);

        Post result = postService.getPostById(post.getId());

        assertNotNull(result);
        assertEquals(post.getId(), result.getId());
        assertEquals(post.getTitle(), result.getTitle());
        assertEquals(post.getContent(), result.getContent());
        verify(postService, times(1)).getPostById(anyLong());
    }

    @Test
    void createPost() {
        Post createPost = new Post(1L,
                "Post1",
                "Author1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Content1",
                "published",
                "Tags",
                new Category(1L, "Teszt", LocalDateTime.now(), LocalDateTime.now()));
        when(postService.createPost(createPost)).thenReturn(createPost);

        Post result = postService.createPost(createPost);

        assertNotNull(result);
        assertEquals(createPost.getId(), result.getId());
        assertEquals(createPost.getTitle(), result.getTitle());
        assertEquals(createPost.getContent(), result.getContent());
        verify(postService, times(1)).createPost(any(Post.class));
    }

    @Test
    void deletePostById() {
        doNothing()
                .when(postService)
                .deletePostById(any(Long.class));
        postService.deletePostById(any(Long.class));
        verify(postService, times(1)).deletePostById(anyLong());
    }
}