package com.glc.iacs__springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glc.iacs__springboot.Model.AppliedStudents;
import com.glc.iacs__springboot.Model.Project;
import com.glc.iacs__springboot.Model.Student;
import com.glc.iacs__springboot.Repository.AppliedStudentsRepository;
import com.glc.iacs__springboot.Repository.ProjectRepository;
import com.glc.iacs__springboot.Repository.StudentRepository;

@RestController
@RequestMapping("/student/applied")
public class AppliedStudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AppliedStudentsRepository appliedStudentsRepository;

    
    @PostMapping
    public ResponseEntity<AppliedStudents> 
    addAppliedStudent( @RequestBody AppliedStudents appliedStudents,
                       @RequestParam("studentId") Long studentId,
                       @RequestParam("projectId") Long projectId ) {
        try {
            
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
            Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
            
            appliedStudents.setStudent(student);
            appliedStudents.setProject(project);
            
            appliedStudentsRepository.save(appliedStudents);
            return ResponseEntity.status(200).body(appliedStudents);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<AppliedStudents>> getAllAppliedStudent(){
        return ResponseEntity.status(200).body(appliedStudentsRepository.findAll());

    }


}
