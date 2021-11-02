package com.devteam.module.security.repository;

import com.devteam.module.security.entity.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface AppPermissonRepository extends JpaRepository<AppPermission, Serializable> {

    List<AppPermission> findByLoginId(String loginId);
}
