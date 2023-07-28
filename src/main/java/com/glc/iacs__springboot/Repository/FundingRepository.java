package com.glc.iacs__springboot.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glc.iacs__springboot.Model.Funding;

import jakarta.transaction.Transactional;
@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {
    public Optional<Funding> findByStudentId(Long studentId);
    
    @Modifying
    @Query("UPDATE Funding f SET f.status = :status WHERE f.id = :fundingId")
    public int updateStatus(@Param("fundingId") Long fundingId, @Param("status") String status);

    public List<Funding> findAllByStatus(String status);

}
