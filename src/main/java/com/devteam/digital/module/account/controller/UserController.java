package com.devteam.digital.module.account.controller;

import com.devteam.digital.core.util.SecurityUtils;
import com.devteam.digital.core.util.exception.BadRequestException;
import com.devteam.digital.module.account.entity.Role;
import com.devteam.digital.module.account.entity.User;
import com.devteam.digital.module.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/digital/users")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("@dev.check('user:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody User resources){
        // Default password 123456
        resources.setPassword(passwordEncoder.encode("123456"));
        userService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
