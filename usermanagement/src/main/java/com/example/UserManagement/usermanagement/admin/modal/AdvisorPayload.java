package com.example.UserManagement.usermanagement.admin.modal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdvisorPayload {
    @NotNull
    private String advisorName;
    @NotNull
    private String advisorPhotoUrl;


}
