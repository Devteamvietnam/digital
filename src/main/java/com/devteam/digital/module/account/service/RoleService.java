package com.devteam.digital.module.account.service;

import com.devteam.digital.module.account.criteria.RoleQueryCriteria;
import com.devteam.digital.module.account.dto.RoleDto;
import com.devteam.digital.module.account.dto.UserDto;
import com.devteam.digital.module.account.entity.Role;
import com.devteam.digital.module.account.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface RoleService {
    
    List<RoleDto> queryAll();

    RoleDto findById(long id);

    void create(Role resources);

    void update(Role resources);

    void delete(Set<Long> ids);

    List<RoleDto> findByUsersId(Long id);

    Integer findByRoles(Set<Role> roles);

    void updateMenu(Role resources, RoleDto roleDTO);

    void untiedMenu(Long id);

    Object queryAll(RoleQueryCriteria criteria, Pageable pageable);


    List<RoleDto> queryAll(RoleQueryCriteria criteria);

    void download(List<RoleDto> queryAll, HttpServletResponse response) throws IOException;

    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);

    void verification(Set<Long> ids);


}
