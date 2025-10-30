package org.example.restexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.restexam.Repository.UserRepository;
import org.example.restexam.domain.User;
import org.example.restexam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final List<User> users = new ArrayList<>();

    private final AtomicLong counter = new AtomicLong();    // id를 자동으로 발급

    // user에 대해서 crud 할 수 있는 메소드를 정의
    // users -- get
    @GetMapping("/users")   // users 전체 조회
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/users/{id}")  // 특정 user 조회
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createUser = userService.addUser(user);
        return ResponseEntity.ok(createUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        User updateUser = userService.updateUser(user);
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/users")
    public User patchUser() {

        return null;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
