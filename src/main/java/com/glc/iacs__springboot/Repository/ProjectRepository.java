package com.glc.iacs__springboot.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glc.iacs__springboot.Model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.active = true AND p.deadlineDate > :currentDate")
    public List<Project> findByActiveIsTrue( Date currentDate );

    @Query("SELECT p FROM Project p WHERE p.active = true AND p.industryId = :industryId AND p.deadlineDate > :currentDate")
    List<Project> findActiveProjectsByIndustryId(Long industryId, Date currentDate);

    @Query("SELECT p FROM Project p WHERE p.deadlineDate > :currentDate")
    List<Project> findAllByDeadlineDateGreaterThan(Date currentDate);

    @Query("SELECT p FROM Project p WHERE p.active = true AND p.deadlineDate > :currentDate AND p.industryId IS NOT NULL")
    List<Project> findActiveProjectsByIndustryId( Date currentDate );
    
}
