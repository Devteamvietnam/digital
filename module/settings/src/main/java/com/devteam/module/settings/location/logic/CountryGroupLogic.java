package com.devteam.module.settings.location.logic;

import java.util.List;

import com.devteam.module.settings.location.model.CountryGroupModel;
import com.devteam.module.settings.location.model.CountryGroupModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.DAOService;
import com.devteam.module.settings.location.entity.Country;
import com.devteam.module.settings.location.entity.CountryCountryGroupRelation;
import com.devteam.module.settings.location.entity.CountryGroup;
import com.devteam.module.settings.location.repository.CountryCountryGroupRelationRepository;
import com.devteam.module.settings.location.repository.CountryGroupRepository;
import com.devteam.module.settings.location.repository.CountryRepository;
import com.devteam.util.ds.Objects;
import com.devteam.util.text.StringUtil;

@Component
public class CountryGroupLogic extends DAOService {

  @Autowired
  private CountryGroupRepository groupRepo;

  @Autowired
  private CountryRepository countryRepo;

  @Autowired
  private CountryCountryGroupRelationRepository groupRelationRepo;

  public CountryGroupModel loadCountryGroupModel(ClientInfo clientInfo, CountryGroupModelRequest req) {
    if(StringUtil.isEmpty(req.getName())) {
      CountryGroupModel model = new CountryGroupModel();
      model.withChildren(groupRepo.findRootChildren());
      return model;
    }
    CountryGroup group = groupRepo.getByName(req.getName());
    if(group == null) return null;
    CountryGroupModel model = new CountryGroupModel(group);
    if(req.isLoadChildren()) {
      model.withChildren(groupRepo.findChildren(group.getId()));
    }
    return model;
  }


  public CountryGroup getCountryGroup(ClientInfo clientInfo, String name) {
    return groupRepo.getByName(name);
  }

  public List<CountryGroup> findChildren(ClientInfo clientInfo, Long groupId) {
    if(groupId <= 0) groupId = null;
    if(groupId == null) return groupRepo.findRootChildren();
    return groupRepo.findChildren(groupId);
  }

  public CountryGroup createCountryGroup(ClientInfo clientInfo, CountryGroup parent, CountryGroup group) {
    group.withParent(parent);
    group.set(clientInfo);
    return groupRepo.save(group);
  }

  public CountryGroup saveCountryGroup(ClientInfo clientInfo, CountryGroup group) {
    group.set(clientInfo);
    return groupRepo.save(group);
  }

  public CountryCountryGroupRelation createCountryGroupRelation(ClientInfo clientInfo, CountryGroup group,
      Country country) {
    return groupRelationRepo.save(new CountryCountryGroupRelation(group, country));
  }

  public boolean createCountryGroupRelations(ClientInfo clientInfo, Long groupId, List<Long> countryIds) {
    CountryGroup group = this.groupRepo.getById(groupId);
    Objects.assertNotNull(group, "Group cannot be null");
    for(Long countryId : countryIds) {
      CountryCountryGroupRelation relation = new CountryCountryGroupRelation();
      relation.set(clientInfo);
      relation.setCountryGroupId(groupId);
      relation.setCountryId(countryId);
      groupRelationRepo.save(relation);
    }
    return true;
  }

  public boolean deleteCountryGroupRelations(ClientInfo clientInfo, Long groupId, List<Long> countryIds) {
    CountryGroup group = this.groupRepo.getById(groupId);
    Objects.assertNotNull(group, "Group cannot be null");
    for(Long countryId : countryIds) {
      CountryCountryGroupRelation relation = groupRelationRepo.getRelationByCountryIdAndCountryGroupId(countryId, groupId);
      groupRelationRepo.delete(relation);
    }
    return true;
  }

  public CountryCountryGroupRelation saveCountryGroupRelation(ClientInfo clientInfo,
      CountryCountryGroupRelation relation) {
    return groupRelationRepo.save(relation);
  }

  public List<CountryGroup> findCountryGroups(ClientInfo clientInfo, Country country) {
    return groupRepo.findByCountry(country.getId());
  }

  public List<Country> findCountries(ClientInfo clientInfo, CountryGroup countryGroup) {
    return countryRepo.findByCountryGroup(countryGroup.getId());
  }
}
