package com.devteam.module.account.logic;

import com.devteam.common.ClientInfo;
import com.devteam.core.data.db.DAOService;
import com.devteam.module.account.entity.UserIdentity;
import com.devteam.module.account.repository.UserIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserLogic extends DAOService {


  @Autowired
  private UserIdentityRepository identityRepo;

  // User identity
  public List<UserIdentity> findUserIdentity(ClientInfo clientInfo, String loginId) {
    return identityRepo.findByLoginId(loginId);
  }

  public List<UserIdentity> saveUserIdentitys(ClientInfo clientInfo, String loginId, List<UserIdentity> identityList) {
    Set<Long> validIdSet = new HashSet<>();
    identityList.forEach(identity -> {
      identity.setLoginId(loginId);
      identity.set(clientInfo);
      identity = identityRepo.save(identity);
      validIdSet.add(identity.getId());
    });
    identityRepo.deleteOrphan(loginId, validIdSet);
    return identityList;
  }

  public UserIdentity saveUserIdentity(ClientInfo client, String loginId, UserIdentity identity) {
    identity.set(client);
    identity.setLoginId(loginId);
    identity = identityRepo.save(identity);
    return identity;
  }

  public boolean deleteUserIdentity(ClientInfo client, String loginId, List<Long> identityIds) {
    identityRepo.delete(loginId, identityIds);
    return true;
  }
}
