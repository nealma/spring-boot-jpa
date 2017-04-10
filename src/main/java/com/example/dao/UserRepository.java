package com.example.dao;

import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by neal on 25/03/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Page<User> findAll(Specification<User> s, Pageable pageRequest);
}
