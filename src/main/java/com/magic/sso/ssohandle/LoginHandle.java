package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class LoginHandle extends SSoHttpHandle{

    public LoginHandle(String path) {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
