package com.devteam.digital.security.service;


import com.devteam.digital.core.util.exception.BadRequestException;
import com.devteam.digital.core.util.exception.EntityNotFoundException;
import com.devteam.digital.module.account.dto.UserDto;
import com.devteam.digital.module.account.service.RoleService;
import com.devteam.digital.module.account.service.UserService;
import com.devteam.digital.security.config.LoginProperties;
import com.devteam.digital.security.service.dto.JwtUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;
    private final LoginProperties loginProperties;

    public void setEnableCache(boolean enableCache) {
        this.loginProperties.setCacheEnable(enableCache);
    }

    /**
     *
     * @see {@link UserCacheClean}
     */
    static Map<String, JwtUserDto> userDtoCache = new ConcurrentHashMap<>();

    @Override
    public JwtUserDto loadUserByUsername(String username) {
        boolean searchDb = true;
        JwtUserDto jwtUserDto = null;
        if (loginProperties.isCacheEnable() && userDtoCache.containsKey(username)) {
            jwtUserDto = userDtoCache.get(username);
            searchDb = false;
        }
        if (searchDb) {
            UserDto user;
            try {
                user = userService.findByName(username);
            } catch (EntityNotFoundException e) {
                // Spring Security will automatically convert UsernameNotFoundException to BadCredentialsException
                throw new UsernameNotFoundException("", e);
            }
            if (user == null) {
                throw new UsernameNotFoundException("");
            } else {
                if (!user.getEnabled()) {
                    throw new BadRequestException("The account is not activated!");
                }
                jwtUserDto = new JwtUserDto(
                        user,
                        roleService.mapToGrantedAuthorities(user)
                );
                userDtoCache.put(username, jwtUserDto);
            }
        }
        return jwtUserDto;
    }
}

