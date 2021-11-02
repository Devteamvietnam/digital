package com.devteam.core.data.db.activity.repository;

import com.devteam.core.data.db.activity.entity.TransactionActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface TransactionActivityRepository extends JpaRepository<TransactionActivity, Serializable> {
}
