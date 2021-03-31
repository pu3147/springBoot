package cc.fbksy.edu.springBoot.user.service;

import cc.fbksy.edu.springBoot.user.ao.UserAo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class UserManager {


    @Autowired
    private StringRedisTemplate template;

    public  UserAo login(UserAo loginInfo){

        if(template.opsForHash().hasKey("userLoginName",loginInfo.getLoginName())){

            return loginInfo;
        }

        return null;
    }
}
