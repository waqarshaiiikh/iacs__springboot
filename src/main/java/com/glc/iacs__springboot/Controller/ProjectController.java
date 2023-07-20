package com.glc.iacs__springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.iacs__springboot.Model.Project;
import com.glc.iacs__springboot.Repository.DepartmentRepository;
import com.glc.iacs__springboot.Repository.ProjectRepository;
import com.glc.iacs__springboot.Repository.SkillRepository;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*")
public class ProjectController {
    
    @Autowired
    private  ProjectRepository projectRepository;
    
    @Autowired 
    private SkillRepository skillRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // public ProjectController(ProjectRepository projectRepository) {
        // this.projectRepository= projectRepository;
    // }
    
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(){
        // @RequestBody Long industryId
        try{
            List <Project> projects =   projectRepository.findByActiveIsTrue();
            projects.forEach(project->{
              List<String> skillName =  skillRepository.findSkillNamesByIds(project.getSkillsId());
              List<String> departmentName =  departmentRepository.findDepartmentNamesByIds(project.getDepartmentsId());
              project.setDepartmentsName(departmentName);
              project.setSkillsName(skillName);
            });
            

            return ResponseEntity.status(200).body( projects );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Project>
     postingProject(@RequestBody Project project)
     {
        try {
              Project saveProject = projectRepository.save(project);
              List<String> skillName =  skillRepository.findSkillNamesByIds(saveProject.getSkillsId());
              List<String> departmentName =  departmentRepository.findDepartmentNamesByIds(saveProject.getDepartmentsId());
              saveProject.setDepartmentsName(departmentName);
              saveProject.setSkillsName(skillName);
              return ResponseEntity.status(200).body(saveProject);

        } catch (Exception e) {

            System.out.println(e.getSuppressed());

            return ResponseEntity.badRequest().build();
        }
    } 

}
