package com.noriservices.noristock.controller;

import com.noriservices.noristock.model.DTO.NewUserDTO;
import com.noriservices.noristock.model.DTO.ResponseUserDTO;
import com.noriservices.noristock.model.DTO.loginDTO;
import com.noriservices.noristock.model.DTO.TokenDTO;
import com.noriservices.noristock.model.User;
import com.noriservices.noristock.model.UserRole;
import com.noriservices.noristock.service.JwtService;
import com.noriservices.noristock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity hello(@RequestBody loginDTO dto){
        boolean userExists = userService.userExistsByUsername(dto.username());
        if(!userExists) return ResponseEntity.badRequest().build();

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        User user =  (User) authentication.getPrincipal();
        if(UserRole.SYSTEM.equals(user.getRole())){
            return ResponseEntity.badRequest().build();
        }
        String token = jwtService.generateToken((UserDetails) Objects.requireNonNull(authentication.getPrincipal()));
        return ResponseEntity.ok().body(new TokenDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody NewUserDTO userDTO){
        if(this.userService.findByUsername(userDTO.username()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        ResponseUserDTO response = userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
