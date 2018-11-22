package com.magic.sso.ssohandle;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class DemoSSoHandle extends SSoHttpHandle{
    public DemoSSoHandle(String path, HttpString method) throws Exception {
        super(path,method);
    }

    public DemoSSoHandle(String path) throws Exception {
        super("/"+path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        System.out.println(exchange.getRequestMethod());
        exchange.getResponseSender().send("sdfsdfsdfsdf");
    }
}
