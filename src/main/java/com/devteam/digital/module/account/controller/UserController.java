package com.devteam.digital.module.account.controller;

import com.devteam.digital.core.config.RsaProperties;
import com.devteam.digital.core.util.RsaUtils;
import com.devteam.digital.core.util.SecurityUtils;
import com.devteam.digital.core.util.exception.BadRequestException;
import com.devteam.digital.module.account.criteria.UserQueryCriteria;
import com.devteam.digital.module.account.dto.RoleDto;
import com.devteam.digital.module.account.dto.UserDto;
import com.devteam.digital.module.account.entity.ResetPassword;
import com.devteam.digital.module.account.entity.Role;
import com.devteam.digital.module.account.entity.User;
import com.devteam.digital.module.account.service.RoleService;
import com.devteam.digital.module.account.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/digital/users")
@RequiredArgsConstructor
public class UserController {

    protected PasswordEncoder passwordEncoder;
    @Autowired
    private  UserService userService;
    @Autowired
    private  RoleService roleService;

    @ApiOperation("Export user data")
    @GetMapping(value = "/download")
    @PreAuthorize("@dev.check('user:list')")
    public void download(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
        userService.download(userService.queryAll(criteria), response);
    }

    @ApiOperation("New users")
    @PostMapping
    @PreAuthorize("@el.check('user:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody User resources){
        checkLevel(resources);
        // Default password 123456
        resources.setPassword(passwordEncoder.encode("123456"));
        userService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("Modify user")
    @PutMapping
    @PreAuthorize("@dev.check('user:edit')")
    public ResponseEntity<Object> update(@Validated(User.Update.class) @RequestBody User resources) throws Exception {
        checkLevel(resources);
        userService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("Modify user: personal center")
    @PutMapping(value = "center")
    public ResponseEntity<Object> center(@Validated(User.Update.class) @RequestBody User resources){
        if(!resources.getId().equals(SecurityUtils.getCurrentUserId())){
            throw new BadRequestException("Cannot modify other people's information");
        }
        userService.updateCenter(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("delete users")
    @DeleteMapping
    @PreAuthorize("@dev.check('user:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        for (Long id : ids) {
            Integer currentLevel =  Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleDto::getLevel).collect(Collectors.toList()));
            Integer optLevel =  Collections.min(roleService.findByUsersId(id).stream().map(RoleDto::getLevel).collect(Collectors.toList()));
            if (currentLevel > optLevel) {
                throw new BadRequestException("Role permissions are insufficient to delete:" + userService.findById(id).getUsername());
            }
        }
        userService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("change Password")
    @PostMapping(value = "/updatePass")
    public ResponseEntity<Object> updatePass(@RequestBody ResetPassword pass) throws Exception {
        String oldPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey,pass.getOldPass());
        String newPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey,pass.getNewPass());
        UserDto user = userService.findByName(SecurityUtils.getCurrentUsername());
        if(!passwordEncoder.matches(oldPass, user.getPassword())){
            throw new BadRequestException("Modification failed, old password is wrong");
        }
        if(passwordEncoder.matches(newPass, user.getPassword())){
            throw new BadRequestException("Modification failed, old password is wrong");
        }
        userService.updatePass(user.getUsername(),passwordEncoder.encode(newPass));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Modify avatar")
    @PostMapping(value = "/updateAvatar")
    public ResponseEntity<Object> updateAvatar(@RequestParam MultipartFile avatar){
        return new ResponseEntity<>(userService.updateAvatar(avatar), HttpStatus.OK);
    }


    /**
     * If the role level of the current user is lower than the role level of the created user, an insufficient authority error will be thrown
     */
    private void checkLevel(User resources) {
        Integer currentLevel =  Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleDto::getLevel).collect(Collectors.toList()));
        Integer optLevel = roleService.findByRoles(resources.getRoles());
        if (currentLevel > optLevel) {
            throw new BadRequestException("Insufficient role permissions");
        }
    }
}
