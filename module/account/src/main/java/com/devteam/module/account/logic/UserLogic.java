package com.devteam.module.account.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.account.entity.UserEducation;
import com.devteam.module.account.entity.UserIdentity;
import com.devteam.module.account.entity.UserRelation;
import com.devteam.module.account.entity.UserWork;
import com.devteam.module.account.repository.UserEducationRepository;
import com.devteam.module.account.repository.UserIdentityRepository;
import com.devteam.module.account.repository.UserRelationRepository;
import com.devteam.module.account.repository.UserWorkRepository;
import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;

@Component
public class UserLogic extends DAOService {
  @Autowired
  private UserEducationRepository eduRepo;

  @Autowired
  private UserIdentityRepository identityRepo;

  @Autowired
  private UserWorkRepository workRepo;

  @Autowired
  private UserRelationRepository relationRepo;


  // User Education
  public List<UserEducation> findUserEducation(ClientInfo clientInfo, String loginId) {
    return eduRepo.findByLoginId(loginId);
  }

  public List<UserEducation> saveUserEducations(ClientInfo clientInfo, String loginId, List<UserEducation> educationList) {
    Set<Long> validIdSet = new HashSet<>();
    educationList.forEach(education -> {
      education.setLoginId(loginId);
      education.set(clientInfo);
      education = eduRepo.save(education);
      validIdSet.add(education.getId());
    });
    eduRepo.deleteOrphan(loginId, validIdSet);
    return educationList;
  }

  public UserEducation saveUserEducation(ClientInfo client, String loginId, UserEducation education) {
    education.set(client);
    education.setLoginId(loginId);
    education = eduRepo.save(education);
    return education;
  }

  public boolean deleteUserEducation(ClientInfo client, String loginId, List<Long> educationIds) {
    eduRepo.delete(loginId, educationIds);
    return true;
  }

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

  // User Work
  public List<UserWork> findUserWork(ClientInfo clientInfo, String loginId) {
    return workRepo.findByLoginId(loginId);
  }

  public List<UserWork> saveUserWorks(ClientInfo clientInfo, String loginId, List<UserWork> workList) {
    Set<Long> validIdSet = new HashSet<>();
    workList.forEach(work -> {
      work.setLoginId(loginId);
      work.set(clientInfo);
      work = workRepo.save(work);
      validIdSet.add(work.getId());
    });
    workRepo.deleteOrphan(loginId, validIdSet);
    return workList;
  }

  public UserWork saveUserWork(ClientInfo client, String loginId, UserWork work) {
    work.set(client);
    work.setLoginId(loginId);
    work = workRepo.save(work);
    return work;
  }

  public boolean deleteUserWork(ClientInfo client, String loginId, List<Long> workIds) {
    workRepo.delete(loginId, workIds);
    return true;
  }

  // User Relatioon
  public List<UserRelation> findUserRelations(ClientInfo clientInfo, String loginId) {
    return relationRepo.findByLoginId(loginId);
  }

  public List<UserRelation> saveUserRelations(ClientInfo clientInfo, String loginId, List<UserRelation> relationList) {
    Set<Long> validIdSet = new HashSet<>();
    relationList.forEach(relation -> {
      relation.setLoginId(loginId);
      relation.set(clientInfo);
      relationRepo.save(relation);
      validIdSet.add(relation.getId());
    });
    workRepo.deleteOrphan(loginId, validIdSet);
    return relationList;
  }

  public UserRelation saveUserRelation(ClientInfo client, String loginId, UserRelation relation) {
    relation.set(client);
    relation.setLoginId(loginId);
    relation = relationRepo.save(relation);
    return relation;
  }

  public boolean deleteUserRelation(ClientInfo client, String loginId, List<Long> relationIds) {
    relationRepo.delete(loginId, relationIds);
    return true;
  }
}
