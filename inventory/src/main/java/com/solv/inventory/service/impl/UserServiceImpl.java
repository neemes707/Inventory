package com.solv.inventory.service.impl;

import com.solv.inventory.dto.UserResponse;
import com.solv.inventory.exceptions.BadArgumentException;
import com.solv.inventory.dao.UserRepository;
import com.solv.inventory.dto.RegisterUserDto;
import com.solv.inventory.entity.User;
import com.solv.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import static com.solv.inventory.mapper.UserMapper.*;
import static com.solv.inventory.mapper.UserMapper.toUser;
import static com.solv.inventory.util.EmailValidator.isValidEmail;
import static com.solv.inventory.util.MobileNumberValidator.isValidMobileNumber;
import static com.solv.inventory.util.NameValidator.*;
import static com.solv.inventory.util.UserTypeValidator.isValidUserType;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String saveUser(String userName) {
        return userName + " User Saved Successfully";
    }

    @Override
    public ResponseEntity<UserResponse> createNewUser(RegisterUserDto registerUserDto) {
       if(!isValidUser(registerUserDto)) {
           UserResponse userResponse =buildResponse1(null,HttpStatus.BAD_REQUEST.toString(),"Invalid Inputs");
           return new ResponseEntity<>(userResponse,HttpStatus.BAD_REQUEST);
       }
        else{
            User user = toUser(registerUserDto);
            user.setCreatedDate(LocalDate.now());
            user.setUpdatedDate(LocalDate.now());
            user = userRepository.save(user);
           UserResponse userResponse =buildResponse1(user,HttpStatus.CREATED.toString(), "User registered successfully");
           return new ResponseEntity<>(userResponse,HttpStatus.OK);
       }
    }
    private UserResponse buildResponse1(Object object, String statusCode, String message){
        return UserResponse.builder()
                .data(object)
                .statusCode(statusCode)
                .statusMessage(message)
                .build();
    }
    private boolean isValidUser(RegisterUserDto registerUserDto)  {
        try{
            isValidName(registerUserDto.getName());
            isValidMobileNumber(registerUserDto.getMobNum());
            isValidEmail(registerUserDto.getEmail());
            isValidUserType(registerUserDto.getUserType());
            return true;
        }
        catch(BadArgumentException ignored){

        }
        return false;
    }
    @Override
    public ResponseEntity<UserResponse> getById(int id) {
        if(!userRepository.findById(id).isPresent()) {
            UserResponse userResponse = buildResponse1(null, HttpStatus.BAD_REQUEST.toString(), "User with id " + id + " does not exists");
            return new ResponseEntity<>(userResponse,HttpStatus.BAD_REQUEST);
        }
        else {
            User user = userRepository.findById(id).get();
            UserResponse userResponse = buildResponse1(user, HttpStatus.OK.toString(), "User with given id exists");
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<UserResponse>getAll()  {
        List<User> userList=this.userRepository.findAll();
        if(userList.isEmpty()) {
            UserResponse userResponse = buildResponse1(null, HttpStatus.OK.toString(), "Currently there are no registered users");
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
        else {
            List<RegisterUserDto> list=toUserDtoList(userList);
            UserResponse userResponse =buildResponse1(list,HttpStatus.OK.toString(), "Accepted");
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<UserResponse> updateById(RegisterUserDto registerUserDto, int id) {
        if(!isValidUser(registerUserDto)){
            UserResponse userResponse = buildResponse1(null,HttpStatus.BAD_REQUEST.toString(), "Invalid Fields");
            return new ResponseEntity<>(userResponse,HttpStatus.BAD_REQUEST);
        }
        else{
            if(!userRepository.findById(id).isPresent()){
                User user=toUser(registerUserDto);
                user.setCreatedDate(LocalDate.now());
                user.setUpdatedDate(LocalDate.now());
                User updatedUser= this.userRepository.save(user);
                UserResponse userResponse = buildResponse1(updatedUser,HttpStatus.OK.toString(), "User Created");
                return new ResponseEntity<>(userResponse,HttpStatus.CREATED);

            }
            else {
                User user = toUser(registerUserDto);
                User userDB = userRepository.findById(id).get();
                if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
                    userDB.setName(user.getName());
                }
                if (Objects.nonNull(user.getMobNum()) && !"".equalsIgnoreCase(user.getMobNum())) {
                    userDB.setMobNum(user.getMobNum());
                }
                if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
                    userDB.setEmail(user.getEmail());
                }
                if (Objects.nonNull(user.getUserType()) && !"".equalsIgnoreCase(String.valueOf(user.getUserType()))) {
                    userDB.setUserType(user.getUserType());
                }
                user.setUpdatedDate(LocalDate.now());
                User updatedUser = this.userRepository.save(userDB);
                UserResponse userResponse = buildResponse1(updatedUser,HttpStatus.OK.toString(), "User Updated");
                return new ResponseEntity<>(userResponse,HttpStatus.OK);
            }
        }
    }
    @Override
    public ResponseEntity<UserResponse> deleteAll() {
        if(this.userRepository.findAll().isEmpty()){
            UserResponse userResponse =buildResponse1(null,HttpStatus.BAD_REQUEST.toString(),"There are no registered users" );
            return new ResponseEntity<>(userResponse,HttpStatus.BAD_REQUEST);
        }
        else {
            this.userRepository.deleteAll();
            UserResponse userResponse = buildResponse1(null, HttpStatus.OK.toString(), "Successfully deleted all users");
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<UserResponse> deleteUserById(int id) {
        if(!userRepository.findById(id).isPresent()) {
            UserResponse userResponse = buildResponse1(null, HttpStatus.BAD_GATEWAY.toString(), "User with id " + id + " is not present");
            return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
        }
        else{
            this.userRepository.deleteById(id);
            UserResponse userResponse =buildResponse1(null,HttpStatus.OK.toString(),"User Deleted");
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }
    }
}