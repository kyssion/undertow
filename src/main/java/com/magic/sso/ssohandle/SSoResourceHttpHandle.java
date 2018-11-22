package com.magic.sso.ssohandle;

import io.undertow.io.IoCallback;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.*;
import io.undertow.util.HttpString;

import java.io.IOException;

public abstract class SSoResourceHttpHandle extends SSoHttpHandle{

    private ResourceManager resourceManager =
            new ClassPathResourceManager(this.getClass().getClassLoader());

    public SSoResourceHttpHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public SSoResourceHttpHandle(String path) throws Exception {
        super(path);
    }

    public void resourceHandler(HttpServerExchange exchange,String path) throws IOException {
        Resource resource= resourceManager.getResource(path);
        resource.serve(exchange.getResponseSender(),exchange, IoCallback.END_EXCHANGE);
    }
}
