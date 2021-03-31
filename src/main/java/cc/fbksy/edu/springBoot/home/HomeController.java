package cc.fbksy.edu.springBoot.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StringRedisTemplate template;

    @GetMapping(value = { "/", "/index"})
    public String welcomeIndex() {
        template.opsForValue().increment("totalSize");
        System.out.println(template.opsForValue().get("totalSize"));
        return "index";
    }
}
