package com.glc.iacs__springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glc.iacs__springboot.Model.Department;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long>{
    
}
