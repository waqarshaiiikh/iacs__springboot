package com.glc.iacs__springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glc.iacs__springboot.Model.AppliedStudents;

public interface AppliedStudentsRepository extends JpaRepository<AppliedStudents, Long> {
    
}
