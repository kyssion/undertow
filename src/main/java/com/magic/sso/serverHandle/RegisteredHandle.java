package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.SSoHttpHandle;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class RegisteredHandle extends SSoHttpHandle {

    public RegisteredHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public RegisteredHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
