package com.glc.iacs__springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glc.iacs__springboot.Model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findByActiveIsTrue();
    
    @Query("SELECT p FROM Project p WHERE p.active = true AND p.industryId = :industryId")
    List<Project> findActiveProjectsByIndustryId(Long industryId);

}
