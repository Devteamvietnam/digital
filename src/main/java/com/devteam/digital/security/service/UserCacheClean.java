package com.devteam.digital.security.service;

import com.devteam.digital.core.util.StringUtil;
import org.springframework.stereotype.Component;

/**
 * @apiNote: Used to clean up the user login information cache, in order to prevent Spring cyclic dependency and security considerations, a separate tool class is formed
 */
@Component
public class UserCacheClean {

    /**
     * Clean up specific user cache information<br>
     * When user information is changed
     */
    public void cleanUserCache(String userName) {
        if (StringUtil.isNotEmpty(userName)) {
            UserDetailsServiceImpl.userDtoCache.remove(userName);
        }
    }

    /**
     * Clean up the cache information of all users<br>
     * If the role authorization information changes, all caches can be invalidated easily
     */
    public void cleanAll() {
        UserDetailsServiceImpl.userDtoCache.clear();
    }
}