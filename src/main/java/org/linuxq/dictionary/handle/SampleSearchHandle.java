package org.linuxq.dictionary.handle;

import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.linuxq.dictionary.data.SearchReq;
import org.linuxq.dictionary.searchTool.KeyFindTool;
import org.linuxq.dictionary.util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class SampleSearchHandle implements Handler<RoutingContext> {
    Logger logger = LoggerFactory.getLogger(SampleSearchHandle.class);
    @Override
    public void handle(RoutingContext event) {
        try {
            List<SearchReq> list = KeyFindTool.tool.findSampleItemList(event.request().getParam("key"));
            event.response().write(JsonUtil.objectToString(list));
            event.next();
        } catch (IOException | ParseException | InvalidTokenOffsetsException e) {
            logger.info("ERROR find a commond error , msg : {0}",e.getMessage());
        }
    }
}
