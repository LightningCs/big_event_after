package com.s.bigevent.controller;

import com.s.bigevent.domain.Result;
import com.s.bigevent.domain.User;
import com.s.bigevent.service.UserService;
import com.s.bigevent.utils.JwtUtil;
import com.s.bigevent.utils.Md5Util;
import com.s.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查找信息
        User user = userService.findByUsername(username);

        if (user != null) {
            if (user.getPassword().equals(Md5Util.getMD5String(password))) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                //生成jwt令牌
                String token = JwtUtil.genToken(claims);
                //将token存储到redis中
                ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                operations.set("token", token, 1000 * 60 * 60);
                return Result.success(token);
            } else return Result.error("密码错误");
        }
        return Result.error("用户不存在");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {

        Map<String,Object> map = ThreadLocalUtil.get();
        //根据用户名称获取用户信息
        User user = userService.findByUsername((String) map.get("username"));

        return Result.success(user);
    }

    @PutMapping("/update")
    /**
     * @RequestBody:自动把请求体里面的JSON转换成实体类对象
     * @Validate:参数校验
     * */
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    //@URL:校验是不是URL地址
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd))
            return Result.error("缺少必要参数");

        Map<String, Object> map = ThreadLocalUtil.get();
        User user = userService.findByUsername((String) map.get("username"));

        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码错误");
        }

        if (!newPwd.equals(rePwd)) {
            return Result.error("密码不一致");
        }

        //删除redis中的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete("token");

        userService.updatePwd(newPwd);
        return Result.success();
    }

    @GetMapping
    public Result<User> getUserDetail(Integer... userId) {
        log.info("获取用户详细信息：{}", userId[0]);

        User user = userService.getUserDetail(userId).get(0);

        return Result.success(user);
    }
}
