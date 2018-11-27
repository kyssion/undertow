package com.magic.sso.ssohandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.Resource;
import io.undertow.util.HttpString;

import java.io.IOException;

public class DemoSSoHandle extends SSoHttpHandle {
    public DemoSSoHandle(String path, HttpString method) throws Exception {
        super(path,method);
    }

    public DemoSSoHandle(String path) throws Exception {
        super("/"+path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        System.out.println(exchange.getRequestMethod());
//        exchange.getResponseSender().send("sdfsdfsdfsdf");

        ClassPathResourceManager classPathResourceManager = new ClassPathResourceManager(this.getClass().getClassLoader(),"");
        Resource resource=classPathResourceManager.getResource("test");
        System.out.println(resource.toString());
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
