package com.magic.sso.util;

import com.magic.sso.bean.CookieResult;
import com.magic.sso.bean.User;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.Cookie;


public class TokenUtil {
    private static final String BASETOKEN = ")3FKklgeFJIE84(**$#$*fj";

    public static String createLoginToken(User user) {
        return createLoginToken(user.getUserId(), user.getPasswordHash());
    }

    public static String createLoginToken(String userId, String passwordHash) {
        return PasswordHashUtil.passwordToHash(userId + "?&*" + BASETOKEN + "^%$" + passwordHash);
    }

    public static boolean tokenIsSame(String token1, String token2) {
        if (StringUtil.isAllBlank(token1, token2)) {
            return false;
        }
        return token1.equals(token2);
    }

    public static CookieResult insertLoginToken(HttpServerExchange exchange,User u,String...url){
        HeaderUtil.responseJSON(exchange);
        //写登入cookie
        Cookie cookie = CookieUtil.writeCookie(exchange, u.getUserId(), u.getPasswordHash());
        //生成jsonp分发cookie列表
        return  CookieUtil.readResultUrl(cookie,url);
    }
}
