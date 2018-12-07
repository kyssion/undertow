package com.magic.sso.util.sql;

public class UserSql {

    public static String FindUserByUserId = "select * from user where user_id= ?";

    public static String addUser = "insert into user (user_id, password_hash, user_years, email, tell, reg_time, status) values (?,?,?,?,?,?,?)";

    public static String deleteUserById = "delete from user where user_id = ?";
}
