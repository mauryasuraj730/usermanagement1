package com.example.UserManagement.usermanagement.user.repository;

import com.example.UserManagement.usermanagement.user.entity.UserAdvisorEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAdvisorRepository extends JpaRepository<UserAdvisorEntity, Integer> {

    UserAdvisorEntity findByUserIdAndAdvisorId(Integer userId, Integer advisorId);

   List<UserAdvisorEntity> findByUserId(Integer userId);

}
