package com.devteam.digital.module.account.repository;

import com.devteam.digital.module.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);
    @Modifying
    @Query(value = "update digital_user set password = ?2 , pwd_reset_time = ?3 where username = ?1",nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    @Modifying
    @Query(value = "update digital_user set email = ?2 where username = ?1",nativeQuery = true)
    void updateEmail(String username, String email);

    @Query(value = "SELECT u.* FROM digital_user u, digital_users_roles r WHERE" +
            " u.user_id = r.user_id AND r.role_id = ?1", nativeQuery = true)
    List<User> findByRoleId(Long roleId);

    void deleteAllByIdIn(Set<Long> ids);

}
