package com.wonjoon.query_optimization.service;

import com.wonjoon.query_optimization.entity.User;
import com.wonjoon.query_optimization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCachedService {

    private final UserRepository userRepository;

    @Cacheable(value = User.CACHE_KEY, key = "#id")
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @CacheEvict(value =  User.CACHE_KEY, key = "#user.id", condition = "#user.id != null")
    public User addUser(User user){
        return userRepository.save(user);
    }

    @CacheEvict(value =  User.CACHE_KEY, key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
