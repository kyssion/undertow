package com.magic.sso.serverHandle;

import com.magic.sso.bean.User;
import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import com.magic.sso.util.PasswordHashUtil;
import com.magic.sso.util.UserUtil;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class RegisteredHandle extends SSoResourceHttpHandle {

    private static String REGISTERUSER = "registerUser";

    private static String REGISTERPAGE = "registerPage";

    public RegisteredHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public RegisteredHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if(exchange.getRequestPath().equals(this.getPath()+REGISTERUSER)){
            Map<String, Deque<String>> params = exchange.getQueryParameters();
            User u =UserUtil.createUserByRegister(exchange);
            this.Registeruser(u);
            return;
        }
        if(exchange.getRequestPath().equals(this.getPath()+REGISTERPAGE)){
            this.RegisterPage(exchange);
        }
    }
    /**
     * 注册用户信息
     *
     * @param user 用户的user信息
     */
    private void Registeruser(User user) {

    }

    private void RegisterPage(HttpServerExchange exchange) {

    }
}
