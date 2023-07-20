package com.glc.iacs__springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glc.iacs__springboot.Model.Department;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long>{
    
    @Query("SELECT d.departmentName FROM Department  d WHERE d.id IN :ids")
    List<String> findDepartmentNamesByIds(List<Long> ids);
}
