package com.example.UserManagement.usermanagement.admin.repository;

import com.example.UserManagement.usermanagement.admin.entity.AdvisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<AdvisorEntity, Integer> {

    AdvisorEntity findByAdvisorName(String advisorName);

}
