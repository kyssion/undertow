package org.linuxq.dictionary.handle;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;


/**
 * 搜索将单信息前置处理
 */
public class SearchBeforeHandle implements Handler<RoutingContext> {
    Logger logger = LoggerFactory.getLogger(SearchBeforeHandle.class);
    @Override
    public void handle(RoutingContext event) {
        logger.info(" req url  : {0}",event.request().uri());
        event.response().setChunked(true);
        event.response().putHeader(HttpHeaders.CONTENT_TYPE,"text/plain;charset=utf-8");
        event.next();
    }
}
