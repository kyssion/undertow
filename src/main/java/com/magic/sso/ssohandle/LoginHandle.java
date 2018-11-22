package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class LoginHandle extends SSoHttpHandle{

    public LoginHandle(String path, HttpString method) {
        super(path, method);
    }

    public LoginHandle(String path) {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
