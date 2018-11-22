package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.SSoHttpHandle;
import com.magic.sso.ssohandle.SSoResourceHttpHandle;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class LoginHandle extends SSoResourceHttpHandle {

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
