package com.solv.inventory.service;

import com.solv.inventory.dto.RegisterUserDto;
import com.solv.inventory.dto.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
     String saveUser(String name);
     ResponseEntity<UserResponse> createNewUser(RegisterUserDto registerUserDto);

     ResponseEntity<UserResponse> getById(int id);

     ResponseEntity<UserResponse>getAll();

     ResponseEntity<UserResponse> updateById(RegisterUserDto registerUserDto, int id);

     ResponseEntity<UserResponse> deleteAll();

     ResponseEntity<UserResponse> deleteUserById(int id);



}