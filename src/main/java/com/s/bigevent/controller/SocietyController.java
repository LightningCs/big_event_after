package com.s.bigevent.controller;

import com.s.bigevent.domain.Result;
import com.s.bigevent.domain.User;
import com.s.bigevent.service.UserService;
import com.s.bigevent.utils.ThreadLocalUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import static com.s.bigevent.en.Constant.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/society")
@Slf4j
@Data
@RequiredArgsConstructor
public class SocietyController {

    private final RedisTemplate redisTemplate;

    private final UserService userService;

    /**
     * 关注用户
     */
    @PostMapping("/interest")
    public Result interest (Integer userId) {
        log.info("关注用户：{}", userId);

        Map<String, Object> map = ThreadLocalUtil.get();


        redisTemplate.opsForSet().add(FANS.value() + userId, map.get("id"));
        redisTemplate.opsForSet().add(INTEREST.value() + map.get("id"), userId);

        return Result.success();
    }

    /**
     * 根据用户id获取关注用户列表
     */
    @GetMapping("/interest/{userId}")
    public Result<List<User>> getInterest (@PathVariable Integer userId) {
        log.info("根据用户id获取关注用户列表：{}", userId);

        return Result.success(getUserList(INTEREST.value() + userId));
    }

    /**
     * 根据用户id获取关注用户列表
     */
    @GetMapping("/fans/{userId}")
    public Result<List<User>> getFans (@PathVariable Integer userId) {
        log.info("根据用户id获取粉丝用户列表：{}", userId);

        return Result.success(getUserList(FANS.value() + userId));
    }

    private List<User> getUserList (String key) {
        Set<Integer> userIds = redisTemplate.opsForSet().members(key);

        if (userIds != null && !userIds.isEmpty()) {
            Integer[] array = userIds.toArray(Integer[]::new);
            return userService.getUserDetail(array);
        }

        return null;
    }
}
