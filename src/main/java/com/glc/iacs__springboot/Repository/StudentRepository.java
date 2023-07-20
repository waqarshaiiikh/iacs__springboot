package com.glc.iacs__springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glc.iacs__springboot.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long>{

    public List<Student> findAll();

}
