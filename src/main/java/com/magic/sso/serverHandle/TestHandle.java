package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import io.undertow.server.HttpServerExchange;
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
        exchange.getResponseSender().send(item);
    }
}
