package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class LoginHandle extends SSoHttpHandle{

    public LoginHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public LoginHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
