package com.sb.spring.authorization.controller;

import com.sb.spring.authorization.user.UserTO;
import com.sb.spring.authorization.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserTO> register(@RequestBody @Valid UserTO userTO) {
        return ResponseEntity.ok().body(userService.create(userTO));
    }
}
