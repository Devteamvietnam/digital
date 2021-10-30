package com.devteam.digital.module.account.service.impl;

import com.devteam.digital.core.config.FileProperties;
import com.devteam.digital.module.account.criteria.UserQueryCriteria;
import com.devteam.digital.module.account.dto.UserDto;
import com.devteam.digital.module.account.entity.User;
import com.devteam.digital.module.account.repository.UserRepository;
import com.devteam.digital.module.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    private final FileProperties properties;


    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public void create(User resources) {

    }

    @Override
    public void update(User resources) throws Exception {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public UserDto findByName(String userName) {
        return null;
    }

    @Override
    public void updatePass(String username, String encryptPassword) {

    }

    @Override
    public Map<String, String> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    public void updateEmail(String username, String email) {

    }

    @Override
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        return null;
    }

    @Override
    public List<UserDto> queryAll(UserQueryCriteria criteria) {
        return null;
    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public void updateCenter(User resources) {

    }
}
