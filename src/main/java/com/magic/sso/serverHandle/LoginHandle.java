package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class LoginHandle extends SSoResourceHttpHandle {

    public LoginHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public LoginHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }

    /**
     * 用户登入处理方法
     * @param exchange
     */
    private void UserLogin(HttpServerExchange exchange){

    }

    /**
     * 用户登出处理方法
     * @param exchange
     */
    private void UserLogOut(HttpServerExchange exchange){

    }

    /**
     * 用户页面展示处理方法
     * @param exchange
     */
    private void userServerPage(HttpServerExchange exchange){

    }
}
