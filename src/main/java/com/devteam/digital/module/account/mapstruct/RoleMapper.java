package com.devteam.digital.module.account.mapstruct;

import com.devteam.digital.core.base.BaseMapper;
import com.devteam.digital.module.account.dto.RoleDto;
import com.devteam.digital.module.account.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

}
