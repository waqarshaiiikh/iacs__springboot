package com.glc.iacs__springboot.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.iacs__springboot.Model.Student;
import com.glc.iacs__springboot.Repository.StudentRepository;


@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")

public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository StudentRepository) {
        this.studentRepository = StudentRepository;
    }


    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        try{
            return ResponseEntity.status(200).body(  studentRepository.findAll() );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
