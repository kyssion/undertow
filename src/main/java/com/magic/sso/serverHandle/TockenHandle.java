package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class TockenHandle extends SSoHttpHandle {

    private static String ADDTOCKEN = "addTocken";
    private static String ISTOCKENTRUE = "isTockenTrue";
    private static String DELETETOCKEN = "deleteTocken";

    public TockenHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public TockenHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (exchange.getRequestPath().equals(this.getPath() + ADDTOCKEN)) {
            this.addTocken(exchange);
            return;
        }
        if (exchange.getRequestPath().equals(this.getPath() + ISTOCKENTRUE)) {
            this.isTockenTrue(exchange);
            return;
        }
        if (exchange.getRequestPath().equals(this.getPath() + DELETETOCKEN)) {
            this.deleteTocken(exchange);
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
     * @param exchange
     */
    private void isTockenTrue(HttpServerExchange exchange) {

    }

    /**
     * 删除令牌信息
     *
     * @param exchange
     */
    private void deleteTocken(HttpServerExchange exchange) {

    }
}
