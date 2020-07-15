package com.bala.spring_blog_api.repository;

import com.bala.spring_blog_api.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostRepositoryTest {

    @MockBean
    private PostRepository postRepository;

    @Test
    void testFindAll() {
        List<Post> posts = postRepository.findAll();

        assertNotNull(posts);
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Optional<Post> optPost = postRepository.findById(any(Long.class));

        assertNotNull(optPost);
        verify(postRepository, times(1)).findById(any(Long.class));
    }

}