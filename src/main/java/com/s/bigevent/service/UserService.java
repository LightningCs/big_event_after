package com.s.bigevent.service;

import com.s.bigevent.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
