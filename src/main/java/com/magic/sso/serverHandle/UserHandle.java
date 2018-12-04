package com.magic.sso.serverHandle;

import com.magic.sso.bean.User;
import com.magic.sso.dao.TestDao;
import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import com.magic.sso.util.MybatisUtil;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Map;


public class UserHandle extends SSoResourceHttpHandle {

    private  String USERLOGIN = this.getPath()+"/userLogin";
    private  String USERLOGOUT = this.getPath()+"/userLogOut";

    public UserHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public UserHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Map<String, Deque<String>> map=exchange.getQueryParameters();
        if(exchange.getRequestPath().equals(USERLOGIN)){
            this.userLogin(map.get("user_id").getFirst(),
                    map.get("password").getFirst());
            return;
        }
        if(exchange.getRequestPath().equals(USERLOGOUT)){
            this.userLogOut(map.get("user_id").getFirst());
            return;
        }

        this.userServerPage(exchange);
    }

    /**
     * 用户登入处理函数
     * @param userId
     * @param password
     * @throws SQLException
     */
    private void userLogin(String userId,String password) throws SQLException {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestDao testDao = MybatisUtil.getMapper(sqlSession,TestDao.class);
        User user = testDao.getUserByUserId(userId,password);
    }

    /**
     * 用户登出成立函数
     * @param userId
     */
    private void userLogOut(String userId){

    }

    /**
     * 用户页面展示处理函数
     * @param exchange
     */
    private void userServerPage(HttpServerExchange exchange){

    }
}

