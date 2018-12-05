package com.magic.sso.serverHandle;

import com.magic.sso.bean.User;
import com.magic.sso.dao.UserDao;
import com.magic.sso.except.BaseExcept;
import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import com.magic.sso.util.MybatisUtil;
import com.magic.sso.util.PasswordHashUtil;
import com.magic.sso.util.ResponseUtil;
import com.magic.sso.util.ResultCodeUtil;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;


public class UserHandle extends SSoResourceHttpHandle {

    private String USERLOGIN = this.getPath() + "/userLogin";
    private String USERLOGOUT = this.getPath() + "/userLogOut";

    public UserHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public UserHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Map<String, Deque<String>> map = exchange.getQueryParameters();
        if (exchange.getRequestPath().equals(USERLOGIN)) {
            this.userLogin(map.get("user_id").getFirst(),
                    map.get("password").getFirst(), exchange);
            return;
        }
        if (exchange.getRequestPath().equals(USERLOGOUT)) {
            this.userLogOut(map.get("user_id").getFirst());
            return;
        }

        this.userServerPage(exchange);
    }

    /**
     * 用户登入处理函数
     *
     * @param userId
     * @param password
     * @throws SQLException
     */
    private void userLogin(String userId, String password, HttpServerExchange exchange) throws SQLException, BaseExcept {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            UserDao userDao = MybatisUtil.getMapper(sqlSession, UserDao.class);
            User user = Objects.requireNonNull(userDao).findUserByIdAndPassword(userId, PasswordHashUtil.passwordToHash(password));
            if (user != null) {
                exchange.getResponseSender().send(ResponseUtil.getResponsUtil(null, ResultCodeUtil.OK));

            } else {
                exchange.getResponseSender().send(ResponseUtil.getResponsUtil(null, ResultCodeUtil.USERID_OR_PASSWORD_ERROR));
            }
        } catch (Exception e) {
            throw new BaseExcept(ResultCodeUtil.USER_REGISTER_ERROR);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 用户登出成立函数
     *
     * @param userId
     */
    private void userLogOut(String userId) {

    }

    /**
     * 用户页面展示处理函数
     *
     * @param exchange
     */
    private void userServerPage(HttpServerExchange exchange) {

    }
}

