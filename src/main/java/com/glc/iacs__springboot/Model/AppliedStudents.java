package com.glc.iacs__springboot.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class AppliedStudents {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String departmentRollNumber ;
    
    @Column(nullable = false)
    private String contact;
    

    @ElementCollection
    @Column(nullable = false)
    private List<Long> skillsId;
    
    
    @Column(columnDefinition = "CLOB", nullable= false)
    private String experience;

    @Column(nullable = false)
    private String advisorName;


    @Column(nullable = false)
    private String advisorEmail;
    

    @Column(nullable = false)
    private String advisorContact;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER , optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER  , optional = false)
    @JoinColumn(name = "project_id")
    private Project project;



    
    public Long getStudentId() {
        if (student != null) {
            return student.getId();
        }
        return null;
    }

    
    public Long getProjectId() {
        if (project != null) {
            return project.getId();
        }
        return null;
    }

}
