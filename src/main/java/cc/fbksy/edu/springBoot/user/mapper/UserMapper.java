package cc.fbksy.edu.springBoot.user.mapper;


import cc.fbksy.edu.springBoot.user.mapper.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface UserMapper {

    //@Select("SELECT * FROM t_mem_user WHERE user_name = #{loginName}")
    UserEntity getUserByCode(String loginName);

    int countTotal(Map<String,Object> paramMap);
}
