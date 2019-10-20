package org.linuxq.dictionary.handle;

import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class SearchAfterHandle implements Handler<RoutingContext> {
    Logger logger = LoggerFactory.getLogger(SearchAfterHandle.class);
    @Override
    public void handle(RoutingContext event) {
        event.response().end();
    }
}
