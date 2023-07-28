package com.glc.iacs__springboot.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "student", schema = "system")
public class Student {

    @Id
    @Column(columnDefinition = "NUMBER(15,0)", nullable = false)
    private Long id;

    @Column(columnDefinition = "VARCHAR2(10 BYTE)", nullable = false)
    private String fname;
    
    @Column(columnDefinition = "VARCHAR2(10 BYTE)", nullable = false)
    private String lname;
    
    
    @Column(columnDefinition = "VARCHAR2(50 BYTE)", nullable = false)
    private String email;
    
    
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR2(73 BYTE)", nullable = false)
    private String password;
    
    
    @Column(columnDefinition = "VARCHAR2(20 BYTE)", nullable = false)
    private String enrollment;
    
    
    @Column(columnDefinition = "VARCHAR2(6 BYTE)", nullable = false)
    private String cgpa;
    
    @Column(columnDefinition = "VARCHAR2(10 BYTE)", nullable = false)
    private String department;
    
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR2(15 BYTE)", nullable = false)
    private String university;
    
    
    @Column(columnDefinition = "VARCHAR2(20 BYTE)", nullable = false)
    private String phonenumber;
    
    @JsonIgnore
    @Column(columnDefinition = "CHAR(1 BYTE)", nullable = false)
    private String year;
    
    
    @JsonIgnore
    @Column(columnDefinition = "CHAR(1 BYTE)", nullable = false)
    private String semester;
    
    @JsonIgnore
    @Column(columnDefinition = "DATE", nullable = true)
    private Date dob;
    
    
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR2(100 BYTE)", nullable = true)
    private String address;
    
    @JsonIgnore
    @Column(columnDefinition = "CHAR(1 BYTE)", nullable = true)
    private String gender;
    
    @Column(columnDefinition = "VARCHAR2(200 BYTE)", nullable = true)
    private String bio;
    
    @Column(columnDefinition = "VARCHAR2(200 BYTE)", nullable = true)
    private String pic;
    
    @JsonIgnore
    @OneToMany( fetch = FetchType.LAZY  , mappedBy = "student", cascade = CascadeType.ALL)
    private Set<AppliedStudents> appliedStudents = new HashSet<>();

}
