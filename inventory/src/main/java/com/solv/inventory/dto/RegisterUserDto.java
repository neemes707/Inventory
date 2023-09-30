package com.solv.inventory.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDto {

    private String name;
    private String email;
    private String mobNum;
    private String userType;
}
