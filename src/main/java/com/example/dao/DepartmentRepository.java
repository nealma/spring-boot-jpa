package com.example.dao;

import com.example.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by neal on 25/03/2017.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
}
