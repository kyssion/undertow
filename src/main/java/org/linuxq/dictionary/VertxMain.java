package org.linuxq.dictionary;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import org.linuxq.dictionary.handle.AccurateSearchHandle;
import org.linuxq.dictionary.handle.SampleSearchHandle;
import org.linuxq.dictionary.handle.SearchAfterHandle;
import org.linuxq.dictionary.handle.SearchBeforeHandle;

public class VertxMain {

    private static final Logger logger = LoggerFactory.getLogger(VertxMain.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServerOptions options = new HttpServerOptions().setLogActivity(true);
        HttpServer server = vertx.createHttpServer(options);
        Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/sample/select")
                .handler(new SearchBeforeHandle())
                .blockingHandler(new SampleSearchHandle())
                .handler(new SearchAfterHandle());

        router.route(HttpMethod.GET, "/accurate/select")
                .handler(new SearchBeforeHandle())
                .blockingHandler(new AccurateSearchHandle())
                .handler(new SearchAfterHandle());

        server.requestHandler(router).exceptionHandler((Throwable::fillInStackTrace)).listen(8888, res -> {
            if (res.succeeded()) {
                logger.info("Server is now listening! port : {0}",8888);
            } else {
                logger.info("Failed to bind!");
            }
        });
    }
}