package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.Deque;
import java.util.Map;

/**
 * 内服服务接口
 */
public class TockenHandle extends SSoHttpHandle {

    private static String ISTOCKENTRUE = "isTockenTrue";

    public TockenHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public TockenHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        if (exchange.getRequestPath().endsWith(ISTOCKENTRUE)) {
            Map<String, Deque<String>> params = exchange.getQueryParameters();
            this.isTockenTrue(params,exchange);
            return;
        }
    }

    /**
     * 添加令牌信息
     *
     * @param exchange
     */
    private void addTocken(HttpServerExchange exchange) {

    }

    /**
     * 判断令牌信息是否正确
     *
     * @param params
     * @param exchange
     */
    private void isTockenTrue(Map<String, Deque<String>> params, HttpServerExchange exchange) {
        try{
            String userId = params.get("userId").getFirst();
        }catch (Exception e){

        }finally {

        }
    }

    /**
     * 删除令牌信息
     *
     * @param exchange
     */
    private void deleteTocken(HttpServerExchange exchange) {

    }
}
