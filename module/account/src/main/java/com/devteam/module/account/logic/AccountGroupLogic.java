package com.devteam.module.account.logic;

import java.util.List;

import com.devteam.module.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.account.entity.AccountGroup;
import com.devteam.module.account.entity.AccountMembership;
import com.devteam.module.account.repository.AccountGroupRepository;
import com.devteam.module.account.repository.AccountMembershipRepository;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.common.ServiceMethodCallback;
import com.devteam.module.data.db.DAOService;
import com.devteam.util.ds.Objects;
import com.devteam.util.error.ErrorType;
import com.devteam.util.error.RuntimeError;

@Component
public class AccountGroupLogic extends DAOService {

  @Autowired
  private AccountGroupRepository groupRepo;

  @Autowired
  private AccountMembershipRepository membershipRepo;

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

  public boolean createAccountMemberships(ClientInfo clientInfo, Long groupId, List<String> accountLoginIds) {
    AccountGroup group = groupRepo.getById(groupId);
    Objects.assertNotNull(group, "Group cannot be null");
    if(Objects.nonNull(accountLoginIds)) {
      for(String accountLoginId : accountLoginIds) {
        AccountMembership membership = new AccountMembership();
        membership.set(clientInfo);
        membership.setLoginId(accountLoginId);
        membership.setGroupPath(group.getPath());
        membershipRepo.save(membership);
      }
    }
    return true;
  }

  public boolean deleteAccountMemberships(ClientInfo clientInfo, Long groupId, List<String> accountLoginIds) {
    AccountGroup group = groupRepo.getById(groupId);
    Objects.assertNotNull(group, "Group cannot be null");
    if(Objects.nonNull(accountLoginIds)) {
      for(String accountLoginId : accountLoginIds) {
        AccountMembership membership = membershipRepo.getMembershipByGroupPathAndLoginId(group.getPath(), accountLoginId);
        membershipRepo.delete(membership);
      }
    }
    return true;
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
    membershipRepo.deleteByGroup(group.getPath());
    groupRepo.delete(group);

    if(callback != null) callback.onPostMethod();
  }
}
