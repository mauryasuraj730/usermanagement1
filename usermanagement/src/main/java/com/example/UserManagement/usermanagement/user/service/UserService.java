package com.example.UserManagement.usermanagement.user.service;

import com.example.UserManagement.usermanagement.admin.entity.AdvisorEntity;
import com.example.UserManagement.usermanagement.admin.repository.AdvisorRepository;
import com.example.UserManagement.usermanagement.config.JwtTokenUtil;
import com.example.UserManagement.usermanagement.user.entity.UserAdvisorEntity;
import com.example.UserManagement.usermanagement.user.entity.UserEntity;
import com.example.UserManagement.usermanagement.user.modal.BookedAdvisorResponse;
import com.example.UserManagement.usermanagement.user.modal.BookingPayload;
import com.example.UserManagement.usermanagement.user.modal.SignInPayload;
import com.example.UserManagement.usermanagement.user.modal.SignUpPayload;
import com.example.UserManagement.usermanagement.user.repository.UserAdvisorRepository;
import com.example.UserManagement.usermanagement.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdvisorRepository advisorRepository;

    @Autowired
    UserAdvisorRepository userAdvisorRepository;

    public Map<String, String> signIn(SignInPayload payload) {
        Map<String, String> map = new HashMap<>();
        UserEntity user = userRepository.findByUserEmail(payload.getEmail().toLowerCase());
        if (user.getUserPassword().equals(payload.getPassword())) {
            if (user != null) {
                String accessToken1 = new JwtTokenUtil().generateToken(payload.getEmail().toLowerCase());
                map.put("jwtToken", accessToken1);
                map.put("userId", String.valueOf(user.getUserId()));
            } else {
                throw new UsernameNotFoundException("User not Found ");
            }
        }
        return map;
    }

    public Map<String, String> createUser(SignUpPayload payload) {
        Map<String, String> map = new HashMap<>();
        UserEntity user = userRepository.findByUserEmail(payload.getEmail().toLowerCase());
        if (user == null) {
            user = new UserEntity();
            user.setUserEmail(payload.getEmail().toLowerCase());
            user.setUserPassword(payload.getPassword());
            user.setUserName(payload.getName());
            user = userRepository.save(user);
            log.info("user created successfully");
            String accessToken1 = new JwtTokenUtil().generateToken(payload.getEmail().toLowerCase());
            map.put("jwtToken", accessToken1);
            map.put("userId", String.valueOf(user.getUserId()));
        } else {
            log.info("user already exists");
            throw new RuntimeException("User Id already Exists");
         }
        return map;
    }

    public List<AdvisorEntity> getAllAdvisor() {
        List<AdvisorEntity> advisorEntityList = advisorRepository.findAll();
        return advisorEntityList;
    }

    public void bookAdvisor(String userId, String advisorId, BookingPayload bookingPayload) {
        UserAdvisorEntity userAdvisorEntity = userAdvisorRepository.findByUserIdAndAdvisorId(Integer.valueOf(userId), Integer.valueOf(advisorId));
        log.info(bookingPayload.getBookingTime());
        if (userAdvisorEntity == null) {
            userAdvisorEntity = new UserAdvisorEntity();
            userAdvisorEntity.setUserId(Integer.valueOf(userId));
            userAdvisorEntity.setAdvisorId(Integer.valueOf(advisorId));
            userAdvisorEntity.setBookingTime(Timestamp.valueOf(bookingPayload.getBookingTime()));
            userAdvisorRepository.save(userAdvisorEntity);
            log.info("  booking details===>" + userAdvisorEntity);
        } else {
            throw new RuntimeException("Advisor has been booked already");
        }
    }

    public List<BookedAdvisorResponse> getAllBookedAdvisor(String userId) {
        List<UserAdvisorEntity> userAdvisorEntityList = userAdvisorRepository.findByUserId(Integer.valueOf(userId));
        List<BookedAdvisorResponse> bookedAdvisorResponseList = new ArrayList<>();
        for (UserAdvisorEntity userAdvisorEntity : userAdvisorEntityList) {
            Optional<AdvisorEntity> advisorEntityOptional = advisorRepository.findById(userAdvisorEntity.getAdvisorId());
            if (advisorEntityOptional.isPresent()) {
                AdvisorEntity advisorEntity1 = advisorEntityOptional.get();
                BookedAdvisorResponse bookedAdvisorResponse1 = new BookedAdvisorResponse();
                bookedAdvisorResponse1.setAdvisorId(String.valueOf(advisorEntity1.getId()));
                bookedAdvisorResponse1.setAdvisorName(advisorEntity1.getAdvisorName());
                bookedAdvisorResponse1.setAdvisorProfilePic(advisorEntity1.getAdvisorPhotoUrl());
                bookedAdvisorResponse1.setBookingId(String.valueOf(userAdvisorEntity.getId()));
                bookedAdvisorResponse1.setBookingTime(String.valueOf(userAdvisorEntity.getBookingTime()));

                bookedAdvisorResponseList.add(bookedAdvisorResponse1);
            }
        }

        return bookedAdvisorResponseList;
    }

}
