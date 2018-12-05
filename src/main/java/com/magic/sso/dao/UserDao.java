package com.magic.sso.dao;

import com.magic.sso.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Insert("insert into user (user_id, password_hash, user_years, email, tell, reg_time, status) values (#{userId},#{passwordHash},#{userYears},#{email},#{tell},#{regTime},#{status})")
    int registerUser(User user);

    @Select("select* from user where user_id = #{uid} and password_hash = #{password}")
    User findUserByIdAndPassword(@Param("uid") String userId , @Param("password") String password);

    @Select("select distinct (count(id)) from user where user_id = #{id}")
    int hasRegisteruser(@Param("id")String userId);
}
