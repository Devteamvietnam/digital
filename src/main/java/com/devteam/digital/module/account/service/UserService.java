package com.devteam.digital.module.account.service;

import com.devteam.digital.module.account.criteria.UserQueryCriteria;
import com.devteam.digital.module.account.dto.UserDto;
import com.devteam.digital.module.account.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    UserDto findById(long id);

    void create(User resources);

    void update(User resources) throws Exception;

    void delete(Set<Long> ids);

    UserDto findByName(String userName);


    void updatePass(String username, String encryptPassword);

    Map<String, String> updateAvatar(MultipartFile file);

    void updateEmail(String username, String email);

    Object queryAll(UserQueryCriteria criteria, Pageable pageable);

    List<UserDto> queryAll(UserQueryCriteria criteria);


    void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException;

    void updateCenter(User resources);
}
