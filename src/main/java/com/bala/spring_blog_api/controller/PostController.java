package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.Post;
import com.bala.spring_blog_api.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private PostService postService;

    @GetMapping("/posts")
    public List<Post> posts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> postById(@PathVariable Long id) {
        Post find = postService.getPostById(id);
        return ResponseEntity.ok().body(find);
    }

    @PostMapping("/posts/create")
    public ResponseEntity<Post> create(@Valid @RequestBody Post createPost) {
        Post newPost = postService.createPost(createPost);
        return ResponseEntity.ok().body(newPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @Valid @RequestBody Post updatePost) {
        Post updated = postService.updatePost(id, updatePost);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        postService.deletePostById(id);

        String response = "{ " +
                "\"type\": \"success\", " +
                "\"message\": \"Post deleted successfully.\"" +
                " }";

        return ResponseEntity.ok().body(response);
    }
}
