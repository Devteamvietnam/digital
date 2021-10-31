package com.devteam.digital.security.service;

import com.devteam.digital.core.util.EncryptUtils;
import com.devteam.digital.core.util.FileUtil;
import com.devteam.digital.core.util.PageUtil;
import com.devteam.digital.core.util.StringUtil;
import com.devteam.digital.security.config.SecurityProperties;
import com.devteam.digital.security.service.dto.JwtUserDto;
import com.devteam.digital.security.service.dto.OnlineUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class OnlineUserService {

    private static final Logger logger = LoggerFactory.getLogger(OnlineUserService.class);

    private final SecurityProperties properties;

    public OnlineUserService(SecurityProperties properties) {
        this.properties = properties;
    }

    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request){
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(), jwtUserDto.getUser().getNickName(), EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    public Map<String,Object> getAll(String filter, Pageable pageable){
        List<OnlineUserDto> onlineUserDtos = getAll(filter);
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(),pageable.getPageSize(), onlineUserDtos),
                onlineUserDtos.size()
        );
    }

    public List<OnlineUserDto> getAll(String filter){
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }

    public void download(List<OnlineUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OnlineUserDto user : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("User Name", user.getUserName());
            map.put("Nick Name", user.getNickName());
            map.put("Login Date", user.getLoginTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }


    public void kickOut(String key){
        key = properties.getOnlineKey() + key;
    }

    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
    }

    public void checkLoginOnUser(String userName, String igoreToken){
        List<OnlineUserDto> onlineUserDtos = getAll(userName);
        if(onlineUserDtos ==null || onlineUserDtos.isEmpty()){
            return;
        }
        for(OnlineUserDto onlineUserDto : onlineUserDtos){
            if(onlineUserDto.getUserName().equals(userName)){
                try {
                    String token =EncryptUtils.desDecrypt(onlineUserDto.getKey());
                    if(StringUtil.isNotBlank(igoreToken)&&!igoreToken.equals(token)){
                        this.kickOut(token);
                    }else if(StringUtil.isBlank(igoreToken)){
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    logger.error("checkUser is error",e);
                }
            }
        }
    }

    @Async
    public void kickOutForUsername(String username) throws Exception {
        List<OnlineUserDto> onlineUsers = getAll(username);
        for (OnlineUserDto onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(username)) {
                String token =EncryptUtils.desDecrypt(onlineUser.getKey());
                kickOut(token);
            }
        }
    }
}
