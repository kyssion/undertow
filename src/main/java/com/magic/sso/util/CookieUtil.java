package com.magic.sso.util;

import com.magic.sso.bean.CookieResult;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.Cookie;
import io.undertow.server.handlers.CookieImpl;

import java.util.ArrayList;
import java.util.List;

public class CookieUtil {
    public static Cookie writeCookie(HttpServerExchange exchange,String writeName,String writeValue){
        Cookie cookie = new CookieImpl(writeName,writeValue);
        exchange.setResponseCookie(cookie);
        return cookie;
    }

    public static List<String> readCookie(HttpServerExchange exchange,String pre){
        List<String> list = new ArrayList<>(4);

        return list;
    }

    /**
     * 返回跨域写对象函数
     * @param url
     * @return
     */
    public static CookieResult readResultUrl(Cookie cookie,String...url) {
        CookieResult cookieResult = new CookieResult();
        cookieResult.setCookeName(cookie.getName());
        cookieResult.setCookeValue(cookie.getValue());
        cookieResult.setUrlList(url);
        return cookieResult;
    }
}
