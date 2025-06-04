package com.example.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;

    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    private Timestamp createTime;
    private Timestamp updateTime;
}

