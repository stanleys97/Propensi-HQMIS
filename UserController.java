package com.project.propensib8.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.Role;
import com.project.propensib8.model.RoleName;
import com.project.propensib8.model.User;
import com.project.propensib8.payload.ApiResponse;
import com.project.propensib8.payload.DeleteUserRequest;
import com.project.propensib8.payload.UpdateRequest;
import com.project.propensib8.payload.UserSummary;
import com.project.propensib8.repository.RoleRepository;
import com.project.propensib8.repository.UserRepository;
import com.project.propensib8.rest.UserResponse;
import com.project.propensib8.security.CurrentUser;
import com.project.propensib8.security.UserPrincipal;
import com.project.propensib8.exception.AppException;
import com.project.propensib8.exception.ResourceNotFoundException;
import com.project.propensib8.model.User;
import com.project.propensib8.payload.*;
import com.project.propensib8.repository.UserRepository;
import com.project.propensib8.security.UserPrincipal;
import com.project.propensib8.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		System.out.println(currentUser.getAuthorities().iterator().next().getAuthority());
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(),
				currentUser.getAuthorities().iterator().next().getAuthority());
		return userSummary;
	}

	@GetMapping("/user/{id}")
	public UserResponse getUser(@PathVariable String id) {
		User user = userRepository.findById(Long.parseLong(id)).get();
		UserResponse res = new UserResponse(user.getId(), user.getName(),
				user.getRoles().iterator().next().getName().toString(), user.getEmail());
		return res;
	}

	@GetMapping("/all-user")
	public ResponseEntity<?> getAllUser() {
		List<User> listUser = userRepository.findAll();
		List<UserResponse> response = new ArrayList<>();
		for (User u : listUser) {
			String role = u.getRoles().iterator().next().getName().toString();
			if (!role.equalsIgnoreCase("ROLE_ADMIN") && !role.equalsIgnoreCase("ROLE_TOP_MANAGER") && !role.equalsIgnoreCase("ROLE_EXECUTIVE")) {
				response.add(new UserResponse(u.getId(), u.getName(),
						u.getRoles().iterator().next().getName().toString(), u.getEmail()));
			}
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@DeleteMapping("/user/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUserRequest deleteRequest) {
		System.out.println(deleteRequest);
		User admin = userRepository.findById(deleteRequest.getIdAdmin()).get();
		if (!passwordEncoder.matches(deleteRequest.getPassword(), admin.getPassword())) {
			return new ResponseEntity(new ApiResponse(false, "Password yang dimasukkan salah!"),
					HttpStatus.BAD_REQUEST);
		}
		User user = userRepository.findById(deleteRequest.getIdUser()).get();
		userRepository.deleteById(deleteRequest.getIdUser());
		return ResponseEntity.accepted().body(new ApiResponse(true, "User " + user.getName() + " berhasil dihapus!"));
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody UpdateRequest updateRequest) {
		User user = userRepository.findById(Long.parseLong(id)).get();
		if (!passwordEncoder.matches(updateRequest.getOldPassword(), user.getPassword())) {
			return new ResponseEntity(new ApiResponse(false, "Password lama yang dimasukkan tidak cocok!"),
					HttpStatus.BAD_REQUEST);
		}
		user.setName(updateRequest.getName());
		user.setEmail(updateRequest.getEmail());
		user.setPassword(passwordEncoder.encode(updateRequest.getNewPassword()));
		userRepository.save(user);
		return ResponseEntity.ok().body(userRepository.findById(Long.parseLong(id)));
	}
}
