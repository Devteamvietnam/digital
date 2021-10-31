package com.devteam.module.account.logic;

import com.devteam.common.ClientInfo;
import com.devteam.common.ServiceMethodCallback;
import com.devteam.core.data.db.DAOService;
import com.devteam.lib.util.error.ErrorType;
import com.devteam.lib.util.error.RuntimeError;
import com.devteam.module.account.entity.AccountGroup;
import com.devteam.module.account.repository.AccountGroupRepository;
import com.devteam.module.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountGroupLogic extends DAOService {

  @Autowired
  private AccountGroupRepository groupRepo;

  public AccountGroup getAccountGroup(ClientInfo client, String path) {
    return groupRepo.getByPath(path);
  }

  public List<AccountGroup> findChildren(ClientInfo client, String parentPath) {
    if(parentPath == null) return groupRepo.findRootChildren();
    return groupRepo.findChildren(parentPath);
  }

  public AccountGroup saveAccountGroup(ClientInfo client, AccountGroup group) {
    return groupRepo.save(group);
  }

  public AccountGroup createAccountGroup(ClientInfo client, Long parentId, AccountGroup group) {
    if(parentId != null) {
      AccountGroup parent = groupRepo.getById(parentId);
      group.setParent(parent);
    } else {
      group.setParentPath(group.calculateParentPath());
    }

    group.set(client);
    return groupRepo.save(group);
  }


  public AccountGroup updateAccountGroup(ClientInfo client, AccountGroup group) {
    if(group.getParentPath() == null) {
      group.setParentPath(group.calculateParentPath());
    }
    group.set(client);
    return groupRepo.save(group);
  }

  public Boolean delete(ClientInfo client, Long id) {
    AccountGroup group = groupRepo.findById(id).get();
    if (group != null) delete(client, group, null);
    return true;
  }

  void delete(ClientInfo client, AccountGroup group, ServiceMethodCallback<AccountService> callback) {
    if (callback != null) callback.onPreMethod();

    List<AccountGroup> children = groupRepo.findChildren(group.getPath());
    if(children.size() > 0) {
      throw new RuntimeError(ErrorType.IllegalState, "Cannot delete  group " + group.getLabel() + ", that has the children");
    }
    groupRepo.delete(group);

    if(callback != null) callback.onPostMethod();
  }
}
