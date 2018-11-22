package com.magic.sso.ssohandle;

import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.Resource;

import java.io.IOException;

public class UserHandle implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        ClassPathResourceManager classPathResourceManager = new ClassPathResourceManager(this.getClass().getClassLoader(),"");
        Resource resource=classPathResourceManager.getResource("test");
        resource.serve(exchange.getResponseSender(),exchange, new IoCallback() {
            @Override
            public void onComplete(HttpServerExchange exchange, Sender sender) {

            }

            @Override
            public void onException(HttpServerExchange exchange, Sender sender, IOException exception) {

            }
        });
    }
}
