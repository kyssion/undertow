package com.magic.sso.serverHandle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magic.sso.bean.CookieResult;
import com.magic.sso.bean.User;
import com.magic.sso.dao.UserDao;
import com.magic.sso.except.BaseExcept;
import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import com.magic.sso.util.*;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.Cookie;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import org.apache.ibatis.io.DefaultVFS;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class RegisteredHandle extends SSoResourceHttpHandle {

    private static String REGISTERUSER = "registerUser";

    public RegisteredHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public RegisteredHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws JsonProcessingException {
        try {
            if (exchange.getRequestPath().endsWith(REGISTERUSER)) {
                Map<String, Deque<String>> params = exchange.getQueryParameters();
                User u = UserUtil.createUserForRegister(params);
                this.Registeruser(u);
                CookieResult cookieResult=TokenUtil.insertLoginToken(exchange,u,params.get("url").getFirst());
                exchange.getResponseSender().send(ResponseUtil.getResponsUtil(cookieResult, ResultCodeUtil.OK));
                return;
            }
            this.RegisterPage(exchange);
        } catch (BaseExcept except) {
            exchange.getResponseSender().send(ResponseUtil.getResponsUtil(null, except.getResultCode()));
        } catch (Exception e) {
            exchange.getResponseSender().send(ResponseUtil.getResponsUtil(null, ResultCodeUtil.SYSTEM_ERROR));
            e.printStackTrace();
        }
    }


    /**
     * 注册用户信息
     *
     * @param user 用户的user信息
     */
    private void Registeruser(User user) throws BaseExcept {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            int hasRegister = userDao.hasRegisteruser(user.getUserId());
            if (hasRegister > 0) {
                throw new BaseExcept(ResultCodeUtil.USER_HAD_REGISTER);
            }

            int isRegiter = userDao.registerUser(user);

            if (isRegiter <= 0) {
                throw new BaseExcept(ResultCodeUtil.USER_REGISTER_ERROR);
            }
        } finally {
            sqlSession.close();
        }
    }

    private void RegisterPage(HttpServerExchange exchange) throws IOException {
        HeaderUtil.responseTEXT(exchange);
        this.resourceHandler(exchange, "test");
    }


    private Cookie writeCookie(HttpServerExchange exchange, String userId, String passwordhash) {
        return CookieUtil.writeCookie(exchange, "L_" + userId, TokenUtil.createLoginToken(userId, passwordhash));
    }
}
