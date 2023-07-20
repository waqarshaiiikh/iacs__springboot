package com.glc.iacs__springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.iacs__springboot.Repository.SkillRepository;
import com.glc.iacs__springboot.Model.Department;
import com.glc.iacs__springboot.Model.Skill;

@RestController
@RequestMapping("/skill")
public class SkillController {
    
    @Autowired
    private SkillRepository repository;

    @GetMapping
    public ResponseEntity< List<Skill>> getSkill(){
        return ResponseEntity.status(200).body(repository.findAll());
    }
    
    @PostMapping
    public  ResponseEntity<Skill> addDepartment(@RequestBody Skill skill){
        return ResponseEntity.status(200).body(repository.save(skill));

    }
}
