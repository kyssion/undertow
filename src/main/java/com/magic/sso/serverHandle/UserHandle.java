package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class UserHandle extends SSoResourceHttpHandle {

    public UserHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public UserHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }


    private void userLogin(HttpServerExchange exchange){

    }

    private void userLogOut(HttpServerExchange exchange){

    }

    private void useServerPage(HttpServerExchange exchange){

    }
}

