package cc.fbksy.edu.springBoot.user.mapper;


import cc.fbksy.edu.springBoot.user.mapper.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserEntity getUserByCode(String loginName);
}
