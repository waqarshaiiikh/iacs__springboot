package com.glc.iacs__springboot.Model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Funding {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; 
    
    private String studentName;
    
    private String studentEmail;
    
    private String studentContact;
    

    private String studentDepartment;
    
    private String studentRollNumber;

    private String projectTitle;

    @Column(columnDefinition = "CLOB")
    private String projectDescription;

    private String projectDuration;

    private String projectAmount;

    @Column(columnDefinition = "CLOB")
    private String amountJustification;

    @Column(columnDefinition = "CLOB")
    private String amountBreakdown;

    private String supervisorName;

    private String supervisorEmail;

    private String supervisorDepartment;

    private String status;

    private Date appliedDate;



    
}
