package com.glc.iacs__springboot.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "skill", schema = "system")
public class Skill {
    
    @Id
    @Column(columnDefinition = "NUMBER", nullable = false)
    private Long id ;

    
    @Column(columnDefinition = "VARCHAR2(25 BYTE)", nullable = true)
    private String skillname;


    

}
