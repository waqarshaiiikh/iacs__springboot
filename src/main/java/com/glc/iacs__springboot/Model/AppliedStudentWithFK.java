package com.glc.iacs__springboot.Model;

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
public class AppliedStudentWithFK {
    private AppliedStudents appliedStudents;
    private Long studentId ; 
    private Long projectId ;
}
