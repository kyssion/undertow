package com.magic.sso.serverHandle;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.magic.sso.ssohandle.baseHandle.SSoResourceHttpHandle;
import com.magic.sso.util.DateBaseUtil;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserHandle extends SSoResourceHttpHandle {

    private  String USERLOGIN = this.getPath()+"userLogin";
    private  String USERLOGOUT = this.getPath()+"userLogOut";

    public UserHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public UserHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if(exchange.getRequestPath().equals(USERLOGIN)){
            this.userLogin(exchange);
            return;
        }
        if(exchange.getRequestPath().equals(USERLOGOUT)){
            this.userLogin(exchange);
            return;
        }

        this.userServerPage(exchange);
    }

    /**
     * 用户登入处理函数
     * @param exchange
     */
    private void userLogin(HttpServerExchange exchange) throws SQLException {
        Connection connection=DateBaseUtil.getDataSource().getConnection();
        PreparedStatement statement= connection.prepareStatement("select * from user where id = ?");
        statement.setString(1,"1");
        ResultSet resultSet= statement.executeQuery();

    }

    /**
     * 用户登出成立函数
     * @param exchange
     */
    private void userLogOut(HttpServerExchange exchange){

    }

    /**
     * 用户页面展示处理函数
     * @param exchange
     */
    private void userServerPage(HttpServerExchange exchange){

    }
}

