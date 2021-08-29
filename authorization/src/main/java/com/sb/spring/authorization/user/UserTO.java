package com.sb.spring.authorization.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserTO {
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String roles;
}
