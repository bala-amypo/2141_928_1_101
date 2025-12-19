package com.example.demo.service.validation;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exception.BadRequestException;

public class UserValidation {

    public static void validateRegister(UserRegisterDto dto) {

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new BadRequestException("User name is required");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Email is required");
        }

        if (!dto.getEmail().contains("@")) {
            throw new BadRequestException("Invalid email format");
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters");
        }
    }
}
