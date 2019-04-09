package com.magic.sso;

import com.magic.sso.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Insert("insert into user (user_id, password_hash, user_years, email, tell, reg_time, status) values (#{userId},#{passwordHash},#{userYears},#{email},#{tell},#{regTime},#{status})")
    int registerUser(User user);

    @Select("select user.*,user_login.token,user_login.login_time,user_login.login_status from user left join user_login on user.user_id=user_login.user_id " +
            "where user.user_id= #{uid} and user.password_hash=#{password}")
    User findUserByIdAndPassword(@Param("uid") String userId, @Param("password") String password);

    @Select("select distinct (count(id)) from user where user_id = #{id}")
    int hasRegisteruser(@Param("id") String userId);

    @Insert("insert into user_login (user_id, token, login_time, login_status) values (#{id},#{token},#{time},1)")
    int insertLoginInfo(@Param("id") String userId, @Param("token") String token, @Param("time") long loginTime);

    @Insert("update user_login set token =#{token} ,login_time = #{time}  where user_id = #{id}")
    int updateUserLoginInfo(@Param("id") String userId, @Param("token") String token, @Param("time") long loginTime);

    @Delete("delete from user_login where user_id = #{id}")
    int deleteAllLoginInfo(@Param("id") String userId);
}
