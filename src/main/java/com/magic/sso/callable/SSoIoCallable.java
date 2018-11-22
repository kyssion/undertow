package com.magic.sso.callable;

import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public class SSoIoCallable implements IoCallback {
    @Override
    public void onComplete(HttpServerExchange exchange, Sender sender) {

    }

    @Override
    public void onException(HttpServerExchange exchange, Sender sender, IOException exception) {

    }
}
