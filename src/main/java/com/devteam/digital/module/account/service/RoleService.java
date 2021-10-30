package com.devteam.digital.module.account.service;

import com.devteam.digital.module.account.criteria.RoleQueryCriteria;
import com.devteam.digital.module.account.entity.Role;
import com.devteam.digital.module.account.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> queryAll();

    Role findById(long id);

    void create(Role resources);

    void update(Role resources);

    void delete(Set<Long> ids);
    
    List<Role> findByUsersId(Long id);

    Integer findByRoles(Set<Role> roles);

    Object queryAll(RoleQueryCriteria criteria, Pageable pageable);

    List<Role> queryAll(RoleQueryCriteria criteria);

    List<GrantedAuthority> mapToGrantedAuthorities(User user);

    void verification(Set<Long> ids);
}
