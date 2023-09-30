package com.solv.inventory.controller;
import com.solv.inventory.dto.RegisterUserDto;
import com.solv.inventory.dto.UserResponse;
import com.solv.inventory.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Services for user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @ApiOperation("- register new user")
    @PostMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return this.userServiceImpl.createNewUser(registerUserDto);
    }
    @ApiOperation("- get all the users")
    @GetMapping("/users")
    public ResponseEntity<UserResponse> getAllUser() {
        return this.userServiceImpl.getAll();
    }
    @ApiOperation("- get an existing user by their id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") int userId){
        return this.userServiceImpl.getById(userId);
    }
    @ApiOperation("-update an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody RegisterUserDto registerUserDto, @PathVariable("id") int id){
        return this.userServiceImpl.updateById(registerUserDto,id);
    }
    @ApiOperation("-deletes all the existing users")
    @DeleteMapping("/users")
    public ResponseEntity<UserResponse> deleteAll() {
        return this.userServiceImpl.deleteAll();
    }
    @ApiOperation("-deletes a user of given id")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("id") int id){
        return this.userServiceImpl.deleteUserById(id);
    }
}