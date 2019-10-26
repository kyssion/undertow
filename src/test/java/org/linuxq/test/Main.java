package org.linuxq.test;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * 新闻实体类
 * @author moonxy
 *
 */

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler((req)->{
            req.response().end("hello world");
        }).listen(8888);
    }
}
