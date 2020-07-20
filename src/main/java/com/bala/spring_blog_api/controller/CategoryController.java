package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.Category;
import com.bala.spring_blog_api.model.Post;
import com.bala.spring_blog_api.service.CategoryService;
import com.bala.spring_blog_api.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5000")
public class CategoryController {

    private CategoryService categoryService;
    private PostService postService;

    @GetMapping("/categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> categoryById(@PathVariable Long id) {
        Category category = categoryService.findOne(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/categories/{id}/posts")
    public List<Post> categoryPosts(@PathVariable Long id) {
        return postService.getPosts().stream()
                .filter(post -> post.getCategory().getId().equals(id))
                .collect(Collectors.toList());
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
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        String response = "{ " +
                "\"type\": \"success\", " +
                "\"message\": \"Category deleted successfully.\"" +
                " }";

        return ResponseEntity.ok().body(response);
    }
}
