package com.s.bigevent.service.impl;

import com.s.bigevent.domain.User;
import com.s.bigevent.mapper.UserMapper;
import com.s.bigevent.service.UserService;
import com.s.bigevent.utils.Md5Util;
import com.s.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username,String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        userMapper.updateAvatar(avatarUrl, (Integer) map.get("id"));
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), (Integer) map.get("id"));
    }

    @Override
    public List<User> getUserDetail(Integer[] userIds) {
        return userMapper.getUserDetail(userIds);
    }
}
