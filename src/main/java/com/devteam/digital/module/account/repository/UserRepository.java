package com.devteam.digital.module.account.repository;

import com.devteam.digital.module.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
