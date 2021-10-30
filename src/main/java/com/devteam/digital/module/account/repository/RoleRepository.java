package com.devteam.digital.module.account.repository;

import com.devteam.digital.module.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    void deleteAllByIdIn(Set<Long> ids);

    @Query(value = "SELECT r.* FROM sys_role r, digital_users_roles u WHERE " +
            "r.role_id = u.role_id AND u.user_id = ?1",nativeQuery = true)
    Set<Role> findByUserId(Long id);


}
