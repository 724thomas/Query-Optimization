package com.wonjoon.query_optimization.controller;

import com.wonjoon.query_optimization.entity.User;
import com.wonjoon.query_optimization.service.UserCachedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cachedUser")
public class UserCachedController {

    private final UserCachedService userCachedService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userCachedService.getUserById(id).orElse(null);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userCachedService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userCachedService.deleteUser(id);
    }
}
