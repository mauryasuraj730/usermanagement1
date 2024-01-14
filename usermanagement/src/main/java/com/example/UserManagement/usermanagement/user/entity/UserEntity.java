package com.example.UserManagement.usermanagement.user.entity;


import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="userentity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
}
