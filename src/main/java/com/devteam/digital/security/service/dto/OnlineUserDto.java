package com.devteam.digital.security.service.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class OnlineUserDto {

    private String userName;

    private String nickName;

    private String key;

    private Date loginTime;

    public void onlineUserDto() {}

    public OnlineUserDto (String userName, String nickName, String key, Date loginTime) {
        this.userName = userName;
        this.nickName = nickName;
        this.key = key;
        this.loginTime = loginTime;
    }
}
