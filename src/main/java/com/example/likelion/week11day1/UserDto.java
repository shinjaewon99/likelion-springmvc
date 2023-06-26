package com.example.likelion.week11day1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotNull
    private String phone;
    @Future
    private LocalDate date;
}
