package com.project.propensib8.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.propensib8.exception.AppException;
import com.project.propensib8.model.Role;
import com.project.propensib8.model.RoleName;
import com.project.propensib8.model.User;
import com.project.propensib8.payload.ApiResponse;
import com.project.propensib8.payload.JwtAuthenticationResponse;
import com.project.propensib8.payload.LoginRequest;
import com.project.propensib8.payload.SignUpRequest;
import com.project.propensib8.payload.DeleteUserRequest;
import com.project.propensib8.repository.RoleRepository;
import com.project.propensib8.repository.UserRepository;
import com.project.propensib8.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        
        System.out.println(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        System.out.println(tokenProvider.getUserIdFromJWT(jwt));
        System.out.println(userRepository.findById(tokenProvider.getUserIdFromJWT(jwt)).get().getName());
        System.out.println(userRepository.findById(tokenProvider.getUserIdFromJWT(jwt)).get().getRoles().iterator().next().getName());
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail()).get();
        Set<Role> userRole = user.getRoles();
        Long userId = user.getId();
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,userRole,userId));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email tersebut sudah terdaftar!"),
                    HttpStatus.BAD_REQUEST);
        }
        if(!this.validatePassword(signUpRequest.getPassword())) {
        	return new ResponseEntity(new ApiResponse(false, "Password minimal 8 karakter dan setidaknya terdiri dari 1 angka."),
        			HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleName role = RoleName.valueOf(signUpRequest.getRole().toUpperCase());
        Role userRole = roleRepository.findByName(role)
                .orElseThrow(() -> new AppException("User Role not set."));
        
        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
    public boolean validatePassword(String password) {
    	System.out.println(password.length() >= 8);
    	System.out.println(Pattern.compile("[a-zA-Z]").matcher(password).find());
    	System.out.println(Pattern.compile("[0-9]").matcher(password).find());
    	if(password.length() >= 8
    			&& Pattern.compile("[a-zA-Z]").matcher(password).find()
    			&& Pattern.compile("[0-9]").matcher(password).find()
    			) {
    		System.out.println(true);
    		return true;
    	}
    	System.out.println(false);
    	return false;
    }
}