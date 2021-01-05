package cn.hellopika.portal.controller.admin;

import cn.hellopika.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public String test(){
       return "/admin/test/test";
    }

    @GetMapping("/test_redis")
    public void testRedis(){
        redisTemplate.opsForValue().set("name", "zhangsan");
    }
}
