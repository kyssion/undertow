package com.magic.sso.util;


import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DateBaseUtil {
    private static final DruidDataSource dataSource = new DruidDataSource();
    private static final String DATESOURCESQLURL="dataSouce.sql.url";
    private static final String DATESOURCESQLPASSWORD="dataSouce.sql.password";
    private static final String DATESOURCESQLUSERNAME="dataSouce.sql.username";
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
        dataSource.setUrl( properties.getProperty(DATESOURCESQLURL));
        dataSource.setPassword(properties.getProperty(DATESOURCESQLPASSWORD));
        dataSource.setUsername(properties.getProperty(DATESOURCESQLUSERNAME));
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
