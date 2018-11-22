package com.magic.sso.ssohandle;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class DemoSSoHandle extends SSoHttpHandle{
    public DemoSSoHandle(String path, HttpString method) {
        super(path, method);
    }

    public DemoSSoHandle(String path) {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
