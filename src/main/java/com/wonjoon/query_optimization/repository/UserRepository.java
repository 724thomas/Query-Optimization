package com.wonjoon.query_optimization.repository;

import com.wonjoon.query_optimization.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
