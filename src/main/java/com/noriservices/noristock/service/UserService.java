package com.noriservices.noristock.service;

import com.noriservices.noristock.model.DTO.NewUserDTO;
import com.noriservices.noristock.model.DTO.ResponseUserDTO;
import com.noriservices.noristock.model.User;
import com.noriservices.noristock.model.converter.UserConverter;
import com.noriservices.noristock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ResponseUserDTO findDTOByUsername(String username){
        User user = (User) repository.findByUsername(username);
        if(user == null) return null;

        return new UserConverter().convertToResponseUserDTO(user);
    }

    public User findByUsername(String username){
        return (User) repository.findByUsername(username);
    }

    public boolean userExistsByUsername(String username){
        return findByUsername(username) != null;
    }


    public ResponseUserDTO save(NewUserDTO dto){
        User user = new UserConverter().convertToEntity(dto);
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        user.setPassword(encryptedPassword);
        repository.save(user);
        return new UserConverter().convertToResponseUserDTO(user);
    }
}
