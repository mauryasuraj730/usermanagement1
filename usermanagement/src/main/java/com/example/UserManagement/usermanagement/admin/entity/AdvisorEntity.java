package com.example.UserManagement.usermanagement.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="advisor")
@Data
public class AdvisorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String advisorName;
    private String advisorPhotoUrl;
}
