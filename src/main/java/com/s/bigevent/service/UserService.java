package com.s.bigevent.service;

import com.s.bigevent.domain.User;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);

    User getUserDetail(Integer userId);
}
