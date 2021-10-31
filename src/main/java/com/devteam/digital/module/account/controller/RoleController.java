package com.devteam.digital.module.account.controller;

import com.devteam.digital.core.util.SecurityUtils;
import com.devteam.digital.core.util.exception.BadRequestException;
import com.devteam.digital.module.account.criteria.RoleQueryCriteria;
import com.devteam.digital.module.account.dto.RoleDto;
import com.devteam.digital.module.account.entity.Role;
import com.devteam.digital.module.account.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/digital/roles")
public class RoleController {

    private final RoleService roleService;

    private static final String ENTITY_NAME = "role";

    @ApiOperation("Get a single role")
    @GetMapping(value = "/{id}")
    @PreAuthorize("@dev.check('roles:list')")
    public ResponseEntity<Object> query(@PathVariable Long id){
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @ApiOperation("Export role data")
    @GetMapping(value = "/download")
    @PreAuthorize("@dev.check('role:list')")
    public void download(HttpServletResponse response, RoleQueryCriteria criteria) throws IOException {
        roleService.download(roleService.queryAll(criteria), response);
    }

    @ApiOperation("Return all characters")
    @GetMapping(value = "/all")
    @PreAuthorize("@dev.check('roles:list','user:add','user:edit')")
    public ResponseEntity<Object> query(){
        return new ResponseEntity<>(roleService.queryAll(),HttpStatus.OK);
    }

    @ApiOperation("Query role")
    @GetMapping
    @PreAuthorize("@dev.check('roles:list')")
    public ResponseEntity<Object> query(RoleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(roleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ApiOperation("New role")
    @PostMapping
    @PreAuthorize("@dev.check('roles:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Role resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        getLevels(resources.getLevel());
        roleService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("Modify role")
    @PutMapping
    @PreAuthorize("@dev.check('roles:edit')")
    public ResponseEntity<Object> update(@Validated(Role.Update.class) @RequestBody Role resources){
        getLevels(resources.getLevel());
        roleService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiOperation("Delete role")
    @DeleteMapping
    @PreAuthorize("@el.check('roles:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        for (Long id : ids) {
            RoleDto role = roleService.findById(id);
            getLevels(role.getLevel());
        }
        // Verify that the user is associated
        roleService.verification(ids);
        roleService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get the user's role level
     * @return /
     */
    private int getLevels(Integer level){
        List<Integer> levels = roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleDto::getLevel).collect(Collectors.toList());
        int min = Collections.min(levels);
        if(level != null){
            if(level < min){
                throw new BadRequestException("Insufficient permissions, your role level：" + min + "，Insufficient permissions, your role level：" + level);
            }
        }
        return min;
    }
}

