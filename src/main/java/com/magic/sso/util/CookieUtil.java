package com.magic.sso.util;

import com.magic.sso.bean.CookieResult;
import com.magic.sso.bean.User;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.Cookie;
import io.undertow.server.handlers.CookieImpl;

import java.util.ArrayList;
import java.util.List;

public class CookieUtil {

    public static final int ONE_DAY = 24 * 60 * 60;

    public static final int SEVEN_DAY = 7 * 24 * 60 * 60;

    public static final int ONE_MONTH_DAY = 30 * 24 * 7 * 7;

    public static Cookie writeCookie(HttpServerExchange exchange, String writeName, String writeValue) {
        return writeCookie(exchange, writeName, writeValue, Integer.MAX_VALUE);
    }

    public static Cookie writeCookie(HttpServerExchange exchange, String writeName, String writeValue, int outTime) {
        Cookie cookie = new CookieImpl(writeName, writeValue);
        cookie.setHttpOnly(true);//设置cookie只读
        cookie.setSecure(true);//设置浏览器仅通过 HTTPS 连接传回 cookie
        cookie.setMaxAge(outTime);
        exchange.setResponseCookie(cookie);
        return cookie;
    }

    public static List<String> readCookie(HttpServerExchange exchange, String pre) {
        List<String> list = new ArrayList<>(4);

        return list;
    }

    /**
     * 返回跨域写对象函数
     *
     * @param url
     * @return
     */
    public static CookieResult readResultUrl(Cookie cookie, String... url) {
        CookieResult cookieResult = new CookieResult();
        cookieResult.setCookeName(cookie.getName());
        cookieResult.setCookeValue(cookie.getValue());
        cookieResult.setUrlList(url);
        return cookieResult;
    }

    public static void deleteCookie(String userId, HttpServerExchange exchange) {
        CookieUtil.writeCookie(exchange, "SSO_"+userId, "",0);
    }

    public static CookieResult insertLoginToken(HttpServerExchange exchange, User u, String... url) {
        HeaderUtil.responseJSON(exchange);
        //写登入cookie
        Cookie cookie = CookieUtil.writeCookie(exchange, "SSO_"+u.getUserId(), u.getToken());
        //生成jsonp分发cookie列表
        return CookieUtil.readResultUrl(cookie, url);
    }
}
