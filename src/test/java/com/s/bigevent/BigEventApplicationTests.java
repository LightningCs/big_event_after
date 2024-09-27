package com.s.bigevent;

import com.s.bigevent.en.Constant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import static com.s.bigevent.en.Constant.*;

@SpringBootTest
class BigEventApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void save() {
        redisTemplate.opsForHash().put("like", 1, 1);
        redisTemplate.opsForHash().put("like", "csh", "wuhu");
    }

    @Test
    void get() {
        Integer value1 = (Integer) redisTemplate.opsForHash().get("like", 1);
        String value2 = (String) redisTemplate.opsForHash().get("like", "csh");
        System.out.println(value1 + "|" + value2);
    }

    @Test
    void enumTest() {
        Constant like = LIKE;
        System.out.println(like.ordinal() + "，" + like.value());
    }
}
