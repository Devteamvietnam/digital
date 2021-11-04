package com.devteam.module.data.db;

import org.springframework.stereotype.Component;

import com.devteam.module.data.db.activity.entity.TransactionActivity;
import com.devteam.util.ds.MapObject;

@Component
public class DataThreadContext {
  private ThreadLocal<MapObject> threadLocal = new ThreadLocal<>() {
    @Override protected MapObject initialValue() {
      return new MapObject();
    }
  };

  public TransactionActivity getCurrentTransactionActivity() {
    MapObject map = threadLocal.get();
    return map.get(TransactionActivity.class);
  }

  public TransactionActivity removeCurrentTransactionActivity() {
    MapObject map = threadLocal.get();
    return map.remove(TransactionActivity.class);
  }

  public void setCurrentTransactionActivity(TransactionActivity tActivity) {
    MapObject map = threadLocal.get();
    map.put(tActivity);
  }

}
