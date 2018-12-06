package com.magic.sso.serverHandle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magic.sso.bean.CookieResult;
import com.magic.sso.bean.User;
import com.magic.sso.except.BaseExcept;
import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import com.magic.sso.util.*;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.Cookie;
import io.undertow.util.HttpString;

import java.util.Deque;
import java.util.Map;

import static com.magic.sso.util.CookieUtil.writeCookie;

public class LoginHandle extends SSoResourceHttpHandle {

    private static String USERLOGIN = "userLogin";
    private static String USERLOGOUT = "userLogOut";

    public LoginHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public LoginHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if(exchange.getRequestPath().equals(this.getPath()+USERLOGIN)){
            this.UserLogin(exchange);
            return;
        }
        if (exchange.getRequestPath().equals(this.getPath()+USERLOGOUT)){
            this.UserLogOut(exchange);
        }
    }

    /**
     * 用户登入处理方法
     * @param exchange
     */
    private void UserLogin(HttpServerExchange exchange) throws JsonProcessingException {
        try {
            if (exchange.getRequestPath().endsWith(USERLOGIN)) {
                Map<String, Deque<String>> params = exchange.getQueryParameters();
                User user =UserUtil.getUserForLogin(params);

                return;
            }
        } catch (BaseExcept except) {
            exchange.getResponseSender().send(ResponseUtil.getResponsUtil(null, except.getResultCode()));
        } catch (Exception e) {
            exchange.getResponseSender().send(ResponseUtil.getResponsUtil(null, ResultCodeUtil.SYSTEM_ERROR));
            e.printStackTrace();
        }
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
