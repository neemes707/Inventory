package com.solv.inventory.mapper;

import com.solv.inventory.dto.RegisterUserDto;
import com.solv.inventory.entity.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static RegisterUserDto toUserDto(User user)
    {
        return RegisterUserDto.builder()
                .email(user.getEmail())
                .mobNum(user.getMobNum())
                .name(user.getName()).
                userType(user.getUserType())
                .build();
    }
    public static User toUser(RegisterUserDto registerUserDto)
    {
        return User.builder()
                .name(registerUserDto.getName())
                .email(registerUserDto.getEmail())
                .mobNum(registerUserDto.getMobNum())
                .userType(registerUserDto.getUserType())
                .build();
    }
    public static List<RegisterUserDto> toUserDtoList(List<User> user)
    {
        return user.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }
    public static List<User> toUserList(List<RegisterUserDto> registerUserDto)
    {
        return registerUserDto.stream().map(UserMapper::toUser).collect(Collectors.toList());
    }
}
