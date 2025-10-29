package org.example.restexam.controller;

import org.example.restexam.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class UserController {

    private final List<User> users = new ArrayList<>();

    private final AtomicLong counter = new AtomicLong();    // id를 자동으로 발급

    // user에 대해서 crud 할 수 있는 메소드를 정의
    // users -- get
    @GetMapping("/users")   // users 전체 조회
    public List<User> getUsers() {

        return users;
    }

    @GetMapping("/users/{id}")  // 특정 user 조회
    public User getUser(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/users")
    public Long createUser(@RequestBody User user) {
        Long id = counter.incrementAndGet();
        User newUser = new User(id, user.getName(), user.getEmail());
        users.add(newUser);
        return id;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user) {

        return null;
    }

    @PatchMapping("/users")
    public User patchUser() {

        return null;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser() {

    }
}
