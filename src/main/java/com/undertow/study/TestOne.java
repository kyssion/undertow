package com.undertow.study;

import io.undertow.Undertow;
import io.undertow.io.Sender;
import io.undertow.server.DefaultResponseListener;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;


public class TestOne {
    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(new HttpHandler() {
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Hello World");
                        exchange.startBlocking();
                        exchange.dispatch(this);
                    }
                }).build();
        server.start();
    }
}

class SimpleErrorPageHandler implements HttpHandler {

    private final HttpHandler next;

    public SimpleErrorPageHandler(final HttpHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        exchange.addDefaultResponseListener(new DefaultResponseListener() {
            @Override
            public boolean handleDefaultResponse(final HttpServerExchange exchange) {
                if (!exchange.isResponseChannelAvailable()) {
                    return false;
                }
                if (exchange.getStatusCode() == 500) {
                    final String errorPage = "<html><head><title>Error</title></head><body>Internal Error</body></html>";
                    exchange.getResponseHeaders().put(Headers.CONTENT_LENGTH, "" + errorPage.length());
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                    Sender sender = exchange.getResponseSender();
                    sender.send(errorPage);
                    return true;
                }
                return false;
            }
        });
        next.handleRequest(exchange);
    }
}