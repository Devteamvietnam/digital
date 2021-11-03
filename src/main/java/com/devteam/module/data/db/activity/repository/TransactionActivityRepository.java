
package com.devteam.module.data.db.activity.repository;

import com.devteam.module.data.db.activity.entity.TransactionActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TransactionActivityRepository extends JpaRepository<TransactionActivity, Serializable> {
}