package com.magic.sso.util;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class HeaderUtil {
    public static void responseJSON(HttpServerExchange exchange){
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json;charset=UTF-8");
    }

    public static void responseTEXT(HttpServerExchange exchange){
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html;charset=UTF-8");
    }
}
