package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.Resource;
import io.undertow.util.HttpString;

import java.io.IOException;

public class DefaultHandle extends SSoHttpHandle {

    public DefaultHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public DefaultHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        ClassPathResourceManager classPathResourceManager = new ClassPathResourceManager(this.getClass().getClassLoader(), "");
        Resource resource = classPathResourceManager.getResource("test");
        resource.serve(exchange.getResponseSender(), exchange, new IoCallback() {
            @Override
            public void onComplete(HttpServerExchange exchange, Sender sender) {

            }

            @Override
            public void onException(HttpServerExchange exchange, Sender sender, IOException exception) {

            }
        });
    }
}
