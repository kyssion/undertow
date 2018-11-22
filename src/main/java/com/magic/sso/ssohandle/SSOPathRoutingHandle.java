package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class SSOPathRoutingHandle implements HttpHandler {

    Logger logger = LoggerFactory.getLogger(SSOPathRoutingHandle.class);

    private ConcurrentHashMap<String, SSoHttpHandle> handleMap = new ConcurrentHashMap<>();

    private String rootPath;

    public SSOPathRoutingHandle() {
        this("");
    }

    public SSOPathRoutingHandle(String rootPath){
        super();
        this.rootPath=rootPath;

    }

    public boolean addSSoHttpHandle(SSoHttpHandle httpHandle) {
        try {
            handleMap.put(httpHandle.getPath(), httpHandle);
        } catch (Exception e) {
            logger.error("添加处理节点失败:error:{}|path:{}|httpHandle:{}", e.getMessage(), httpHandle.getPath(), httpHandle.toString());
            return false;
        }
        return true;
    }

    public boolean addSSoHttpHandle(ConcurrentHashMap httpHandle) {
        try {
            handleMap.putAll(httpHandle);
        } catch (Exception e) {
            logger.error("批量添加处理节点失败:error:{}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        logger.info("请求传入参数:{}", exchange.getRequestURL());
        System.out.println(this.rootPath+exchange.getRequestPath());
        SSoHttpHandle httpHandle = handleMap.get(exchange.getRequestPath());

        if(httpHandle==null){
            exchange.getResponseSender().send("error");
            return;
        }

        if (exchange.getRequestMethod().equals(httpHandle.getMethod())) {
            exchange.dispatch(httpHandle);
        }
    }
}
