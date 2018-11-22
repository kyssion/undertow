package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class SSOPathRoutingHandle implements HttpHandler {

    Logger logger = LoggerFactory.getLogger(SSOPathRoutingHandle.class);

    private ConcurrentHashMap<String,SSoHttpHandle> handleMap = new ConcurrentHashMap<>();

    public SSOPathRoutingHandle(){
        super();
    }

    public boolean addSSoHttpHandle(SSoHttpHandle httpHandle){
        try {
            handleMap.put(httpHandle.getPath(), httpHandle);
        }catch (Exception e){
            logger.error("添加处理节点失败:error:{}|path:{}|httpHandle:{}",e.getMessage(),httpHandle.getPath(),httpHandle.toString());
            return false;
        }
        return true;
    }

    public boolean addSSoHttpHandle(ConcurrentHashMap httpHandle){
        try {
            handleMap.putAll(httpHandle);
        }catch (Exception e){
            logger.error("批量添加处理节点失败:error:{}",e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
