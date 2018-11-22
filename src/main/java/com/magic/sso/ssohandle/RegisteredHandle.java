package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class RegisteredHandle extends SSoHttpHandle {

    public RegisteredHandle(String path, HttpString method) {
        super(path, method);
    }

    public RegisteredHandle(String path) {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
