package com.example.UserManagement.usermanagement.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name= "UserAdvisorEntity")
public class UserAdvisorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer advisorId;
    private Timestamp bookingTime;



}
