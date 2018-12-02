package com.magic.sso.util;


import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DateBaseUtil {
    private static final DruidDataSource dataSource = new DruidDataSource();
    private static final String DATESOURCESQLURL = "dataSouce.sql.url";
    private static final String DATESOURCESQLPASSWORD = "dataSouce.sql.password";
    private static final String DATESOURCESQLUSERNAME = "dataSouce.sql.username";

    static {
        Properties properties = new Properties();
        InputStream in = DateBaseUtil.class.getClassLoader().getResourceAsStream("config/config.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取key对应的value值
        dataSource.setUrl(properties.getProperty(DATESOURCESQLURL));
        dataSource.setPassword(properties.getProperty(DATESOURCESQLPASSWORD));
        dataSource.setUsername(properties.getProperty(DATESOURCESQLUSERNAME));
    }

    /**
     * 请求参数封装
     *
     * @param connection
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static PreparedStatement createPrePatedStatement(Connection connection, String sql, SqlParams params) throws SQLException {
        String[] paralist = params.getParams();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int a = 0; a < paralist.length; a++) {
            statement.setString(a + 1, paralist[a]);
        }
        return statement;
    }

    public static void batchSQL(Connection connection, String sql, SqlParams[] params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        String[] para;
        for (SqlParams params1 : params) {
            para = params1.getParams();
            for (int a = 0; a < para.length; a++) {
                statement.setString(a + 1, para[a]);
            }
            statement.addBatch();
        }
        statement.executeBatch();
    }

    /**
     * 返回结果集
     *
     * @param statement
     * @return
     * @throws SQLException
     */
    public static ResultSet getResultSet(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }

    public static String getDATESOURCESQLURL() {
        return DATESOURCESQLURL;
    }

    public static String getDATESOURCESQLPASSWORD() {
        return DATESOURCESQLPASSWORD;
    }

    public static String getDATESOURCESQLUSERNAME() {
        return DATESOURCESQLUSERNAME;
    }
}
