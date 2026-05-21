package com.noriservices.noristock.model.converter;

import com.noriservices.noristock.model.DTO.NewUserDTO;
import com.noriservices.noristock.model.DTO.ResponseUserDTO;
import com.noriservices.noristock.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public class UserConverter {

    public ResponseUserDTO convertToResponseUserDTO(User user){
       return new ResponseUserDTO(user.getUsername(),
               user.getName(),
               user.getEmail(),
               user.getRole(),
               user.isActive(),
               user.getCreatedAt(),
               user.getUpdatedAt());
    }

    public User convertToEntity(NewUserDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(dto.role());
        return user;
    }
}
