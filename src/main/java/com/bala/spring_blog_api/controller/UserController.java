package com.bala.spring_blog_api.controller;

import com.bala.spring_blog_api.model.User;
import com.bala.spring_blog_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5000")
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public List<User> users() {
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> userById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users/create")
    public ResponseEntity<User> create(@Valid @RequestBody User newUser) {
        User user = userService.createUser(newUser);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User updated = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);

        String response = "{ " +
                "\"type\": \"success\", " +
                "\"message\": \"User deleted successfully.\"" +
                " }";

        return ResponseEntity.ok().body(response);
    }
}
