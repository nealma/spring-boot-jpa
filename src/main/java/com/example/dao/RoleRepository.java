package com.example.dao;

import com.example.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by neal on 25/03/2017.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
}
