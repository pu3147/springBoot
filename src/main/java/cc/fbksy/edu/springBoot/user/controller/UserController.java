package cc.fbksy.edu.springBoot.user.controller;

import cc.fbksy.edu.springBoot.user.ao.UserAo;
import cc.fbksy.edu.springBoot.user.service.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private StringRedisTemplate template;

    @PostMapping(value = "/login")
    @ResponseBody
    public UserAo doLogin(UserAo loinInfo){
        template.opsForValue().increment("totalAccessSize");

        if(log.isDebugEnabled()){
            log.debug("totalAccessSize:"+template.opsForValue().get("totalAccessSize"));
        }

        return  userManager.login(loinInfo);
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
}
