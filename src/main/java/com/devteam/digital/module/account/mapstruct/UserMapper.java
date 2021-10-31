package com.devteam.digital.module.account.mapstruct;

import com.devteam.digital.core.base.BaseMapper;
import com.devteam.digital.module.account.dto.UserDto;
import com.devteam.digital.module.account.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {RoleMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}
