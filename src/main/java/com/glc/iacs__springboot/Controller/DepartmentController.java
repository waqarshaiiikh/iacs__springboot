package com.glc.iacs__springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.iacs__springboot.Model.Department;
import com.glc.iacs__springboot.Repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments(){
        return ResponseEntity.status(200).body(departmentRepository.findAll());
    }

    @PostMapping
    public  ResponseEntity<Department> addDepartment(@RequestBody Department departmentName){
        return ResponseEntity.status(200).body(departmentRepository.save(departmentName));

    }
}
