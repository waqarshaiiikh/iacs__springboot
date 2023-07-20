package com.glc.iacs__springboot.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
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

    // @GetMapping("/list")
    // public ResponseEntity<List<String>> getListSkill() {
    //     List<Long> skillIds = List.of(1L, 2L, 3L, 4L, 5L); // Convert the list of integers to a list of Longs
    //     return ResponseEntity.status(200).body(repository.findSkillNamesByIds(skillIds));
    // }
    
}
