package org.linuxq.dictionary.handle;

import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import org.linuxq.dictionary.searchTool.DocItem;
import org.linuxq.dictionary.searchTool.KeyFindTool;
import org.linuxq.dictionary.util.JsonUtil;

import java.io.IOException;

public class AccurateSearchHandle implements Handler<RoutingContext> {

    Logger logger = LoggerFactory.getLogger(AccurateSearchHandle.class);

    @Override
    public void handle(RoutingContext event) {
        try {
            DocItem docItem = KeyFindTool.tool.findAccurateItem(event.request().getParam("key"));
            event.response().write(JsonUtil.objectToString(docItem));
            event.next();
        } catch (IOException e) {
            logger.info("ERROR find a commond error , msg : {0}", e.getMessage());
        }
    }
}
