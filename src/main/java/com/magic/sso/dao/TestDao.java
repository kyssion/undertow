package com.magic.sso.dao;

import com.magic.sso.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestDao {

    @Select("select * from user where user_id = #{id}")
    User getUserByUserId(User user);

}
