package cc.fbksy.edu.springBoot.user.service;

import cc.fbksy.edu.springBoot.user.ao.UserAo;
import cc.fbksy.edu.springBoot.user.mapper.UserMapper;
import cc.fbksy.edu.springBoot.user.mapper.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserManager {


    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private UserMapper userMapper;

    public  UserAo login(UserAo loginInfo){

        if(template.opsForHash().hasKey("userLoginName",loginInfo.getLoginName())){
            return loginInfo;
        }

        UserEntity userEntity = userMapper.getUserByCode(loginInfo.getLoginName());

        Map<String,Object> paramMap = new HashMap<String,Object>(2);
        paramMap.put("loginName",loginInfo.getLoginName());
        int size = userMapper.countTotal(paramMap);

        if(null != userEntity){
            return loginInfo;
        }

        return null;
    }
}
