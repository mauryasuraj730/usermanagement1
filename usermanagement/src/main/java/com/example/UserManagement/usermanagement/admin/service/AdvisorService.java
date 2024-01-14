package com.example.UserManagement.usermanagement.admin.service;

import com.example.UserManagement.usermanagement.admin.entity.AdvisorEntity;
import com.example.UserManagement.usermanagement.admin.modal.AdvisorPayload;
import com.example.UserManagement.usermanagement.admin.repository.AdvisorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdvisorService {

    @Autowired
    AdvisorRepository advisorRepository;

    public void saveAdvisor(AdvisorPayload advisorPayload) {
        AdvisorEntity advisorEntity = advisorRepository.findByAdvisorName(advisorPayload.getAdvisorName());
        if (advisorEntity == null) {
            advisorEntity = new AdvisorEntity();
            advisorEntity.setAdvisorName(advisorPayload.getAdvisorName());
            advisorEntity.setAdvisorPhotoUrl(advisorPayload.getAdvisorPhotoUrl());
            advisorRepository.save(advisorEntity);
            log.info(" record saved successfully ");
        } else {
            log.info(" record saved successfully ");
        }
    }

}
