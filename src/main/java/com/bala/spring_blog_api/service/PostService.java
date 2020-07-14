package com.bala.spring_blog_api.service;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.model.Post;
import com.bala.spring_blog_api.repository.CategoryRepository;
import com.bala.spring_blog_api.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private CategoryService categoryService;

    /**
     * Get all Post
     */
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    /**
     * Get one Post
     */
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found on: " + id));
    }

    /**
     * Create Post
     */
    public Post createPost(Post createPost) {
        Long categoryId = createPost.getCategory().getId();
        Category postCategory = categoryService.findOne(categoryId);

        createPost.setCreatedAt(LocalDateTime.now());
        createPost.setUpdatedAt(LocalDateTime.now());
        createPost.setCategory(postCategory);

        return postRepository.save(createPost);
    }

    /**
     * Update Post
     */
    public Post updatePost(Long id, Post updatePost) {
        Long categoryId = updatePost.getCategory().getId();
        Category postCategory = categoryService.findOne(categoryId);
        updatePost.setCategory(postCategory);

        Post find = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found on: " + id));
        find.setTitle(updatePost.getTitle());
        find.setAuthor(updatePost.getAuthor());
        find.setUpdatedAt(LocalDateTime.now());
        find.setContent(updatePost.getContent());
        find.setStatus(updatePost.getStatus());
        find.setTags(updatePost.getTags());
        find.setCategory(updatePost.getCategory());

        return postRepository.save(find);
    }

    /**
     * Delete a Post
     */
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
