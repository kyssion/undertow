package com.magic.sso.util;

import com.magic.sso.bean.User;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class UserUtil {

    public static User createUserByRegister(HttpServerExchange exchange){
        Map<String, Deque<String>> params = exchange.getQueryParameters();
        String userId = params.get("userId").getFirst();
        String password_f = params.get("passwordFir").getFirst();
        String password_e = params.get("passwordEnd").getFirst();
        String userYear = params.get("userYear").getFirst();
        String userTell = params.get("tell").getFirst();
        String email = params.get("email").getFirst();
        return creatUser(userId,PasswordHashUtil.passwordToHash(password_f),
                userYear,userTell,email);
    }

    public static User creatUser(String userId,String password,String userYear,String tell,String email){
        return new User (userId,password,userYear,tell,email);
    }

    public static String isUserIdFormatCorrect(String userId){
        return userId;
    }

    public static String isPasswordFormatCorrect(String userId){
        return userId;
    }

    public static String isEmailFormatCorrect(String userId){
        return userId;
    }

    public static String isTellFormatCorrect(String userId) {
        return userId;
    }
}
