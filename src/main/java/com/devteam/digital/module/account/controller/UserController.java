package com.devteam.digital.module.account.controller;

import com.devteam.digital.core.util.SecurityUtils;
import com.devteam.digital.core.util.exception.BadRequestException;
import com.devteam.digital.module.account.entity.Role;
import com.devteam.digital.module.account.entity.User;
import com.devteam.digital.module.account.service.RoleService;
import com.devteam.digital.module.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/digital/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("@dev.check('user:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody User resources){
        userService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("@dev.check('user:edit')")
    public ResponseEntity<Object> update(@Validated(User.Update.class) @RequestBody User resources) throws Exception {
        userService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @PreAuthorize("@dev.check('user:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        for (Long id : ids) {
            Integer optLevel =  Collections.min(roleService.findByUsersId(id).stream().map(Role::getLevel).collect(Collectors.toList()));
        }
        userService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
