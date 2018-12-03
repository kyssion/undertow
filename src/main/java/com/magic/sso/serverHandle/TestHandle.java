package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;
import io.undertow.util.HttpString;

public class TestHandle extends SSoHttpHandle {
    public TestHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    private String item ;
    public TestHandle(String path,String item) throws Exception {
        super(path);
        this.item = item;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        String s = exchange.getQueryParameters().get("i").getFirst();
        System.out.println(s);
        HeaderValues contentType= exchange.getResponseHeaders().get("contentType");
        System.out.println(contentType.get(0));
        exchange.getResponseSender().send("中国");
    }
}
