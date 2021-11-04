
package com.devteam.module.data.db.activity.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devteam.module.data.db.activity.entity.TransactionActivity;

public interface TransactionActivityRepository extends JpaRepository<TransactionActivity, Serializable> {
}