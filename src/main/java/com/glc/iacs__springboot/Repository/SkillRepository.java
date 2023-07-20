package com.glc.iacs__springboot.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glc.iacs__springboot.Model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("SELECT s.skillname FROM Skill s WHERE s.id IN :ids")
    List<String> findSkillNamesByIds(List<Long> ids);
}
