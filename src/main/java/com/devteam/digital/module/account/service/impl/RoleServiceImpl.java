package com.devteam.digital.module.account.service.impl;

import com.devteam.digital.core.util.StringUtil;
import com.devteam.digital.core.util.ValidationUtil;
import com.devteam.digital.core.util.exception.BadRequestException;
import com.devteam.digital.core.util.exception.EntityExistException;
import com.devteam.digital.module.account.criteria.RoleQueryCriteria;
import com.devteam.digital.module.account.entity.Role;
import com.devteam.digital.module.account.entity.User;
import com.devteam.digital.module.account.repository.RoleRepository;
import com.devteam.digital.module.account.repository.UserRepository;
import com.devteam.digital.module.account.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> queryAll() {
        return roleRepository.findAll();
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    @Transactional(rollbackFor = Exception.class)
    public Role findById(long id) {
        Role role = roleRepository.findById(id).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(), "Role", "id", id);
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Role resources) {
        if (roleRepository.findByName(resources.getName()) != null) {
            throw new EntityExistException(Role.class, "username", resources.getName());
        }
        roleRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role resources) {
        Role role = roleRepository.findById(resources.getId()).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(), "Role", "id", resources.getId());

        Role role1 = roleRepository.findByName(resources.getName());

        if (role1 != null && !role1.getId().equals(role.getId())) {
            throw new EntityExistException(Role.class, "username", resources.getName());
        }
        role.setName(resources.getName());
        role.setDescription(resources.getDescription());
        role.setDataScope(resources.getDataScope());
        role.setLevel(resources.getLevel());
        roleRepository.save(role);
    }

    @Override
    public void delete(Set<Long> ids) {
        roleRepository.deleteAllByIdIn(ids);
    }

    @Override
    public List<Role> findByUsersId(Long id) {
        return new ArrayList<>(roleRepository.findByUserId(id));
    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        if (roles.size() == 0) {
            return Integer.MAX_VALUE;
        }
        Set<Role> findRole = new HashSet<>();
        for (Role role : roles) {
            findRole.add(findById(role.getId()));
        }
        return Collections.min(findRole.stream().map(Role::getLevel).collect(Collectors.toList()));
    }

    @Override
    public Object queryAll(RoleQueryCriteria criteria, Pageable pageable) {
        return null;
    }

    @Override
    public List<Role> queryAll(RoleQueryCriteria criteria) {
        return null;
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(User user) {
        Set<String> permissions = new HashSet<>();
        // If it is an administrator, return directly
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        Set<Role> roles = roleRepository.findByUserId(user.getId());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public void verification(Set<Long> ids) {
        if (userRepository.countByRoles(ids) > 0) {
            throw new BadRequestException("The selected role has user associations, please unlink and try again!");
        }
    }
}
