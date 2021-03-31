package cc.fbksy.edu.springBoot.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private StringRedisTemplate template;

    @GetMapping(value = { "/", "/index"})
    public String welcomeIndex() {
        template.opsForValue().increment("totalSize");
        if(log.isDebugEnabled()){
            log.debug("totalSize={}",template.opsForValue().get("totalSize"));
        }
        return "index";
    }
}
