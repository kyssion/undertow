package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

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
            this.Registeruser(exchange);
            return;
        }
        if(exchange.getRequestPath().equals(this.getPath()+REGISTERPAGE)){
            this.RegisterPage(exchange);
        }
    }

    /**
     * 注册用户信息
     *
     * @param exchange
     */
    private void Registeruser(HttpServerExchange exchange) {

    }

    private void RegisterPage(HttpServerExchange exchange) {

    }
}
