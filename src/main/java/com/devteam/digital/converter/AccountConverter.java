package com.devteam.digital.converter;

import com.devteam.digital.dto.AccountDto;
import com.devteam.digital.dto.ERole;
import com.devteam.digital.entity.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountConverter extends BaseConverter {

    public AccountConverter() {}

    public Account dtoToEntity(AccountDto dto) {
        return map(dto, Account.class);
    }

    public AccountDto entityToDto(Account e) {
        AccountDto u = map(e, AccountDto.class);
        u.getRoles().clear();
        e.getAccountRoles().stream().forEach(i -> {
            if(i == ERole.ROLE_ADMIN) {
                u.getRoles().add("admin");
            } else if(i == ERole.ROLE_MODERATOR) {
                u.getRoles().add("mod");
            } else {
                u.getRoles().add("user");
            }
        });




        return u;
    }

    public List<Account> dtoToEntityList(List<AccountDto> list){
        return list.stream().map(i -> { return dtoToEntity(i); }).collect(Collectors.toList());
    }

    public List<AccountDto> entityToDtoList(List<Account> list){
        return list.stream().map(i -> { return entityToDto(i); }).collect(Collectors.toList());
    }

}
