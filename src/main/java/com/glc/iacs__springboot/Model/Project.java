package com.glc.iacs__springboot.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // @ElementCollection
    // @Column(nullable = false)
    // private List<Long> skillsId;

    @Column(columnDefinition = "CLOB", nullable = false)
    @Lob
    private String statement;

    @Column(columnDefinition = "CLOB", nullable = false)
    @Lob
    private String description;


    
    @Column(columnDefinition = "CLOB", nullable = false)
    @Lob
    private String scope;

    @Column(columnDefinition = "CLOB", nullable = false)
    @Lob
    private String deliverables;

    @Column(columnDefinition = "CLOB", nullable = false)
    @Lob
    private String methodology;


    @Column(nullable = false)
    private Long departmentId;

    @Column(nullable = false)
    private String contact;
    
    @Column(nullable = true)
    private Date postDate;

    @Column(nullable = true)
    private Date deadlineDate;

    // @Transient
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ElementCollection
    @Column(nullable = false)
    private List<String> skillsName;
    
    
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)    
    private String departmentName;
    
    /**
     * use for active and deactivated the project
     */

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Long industryId;



    /**
     * now set json ignore for this field when getting the project otherwise set the applied project is allowed.
     */
    // @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<AppliedStudents> appliedStudents = new HashSet<AppliedStudents>();
    

}
