package com.magic.sso.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;


public class MybatisUtil {

    private static final SqlSessionFactoryBuilder sqlSessionFactoryBuilder=
            new SqlSessionFactoryBuilder();

    private static SqlSessionFactory factory=null;

    static {
        try {
            factory=sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis/mybtais.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  <T> T getMapper(SqlSessionFactory factory, Class<T> tClass,boolean isAutoCommit){
        if(null==factory){
            return null;
        }
        return factory.openSession(isAutoCommit).getMapper(tClass);
    }

    public static  <T> T getMapper(SqlSessionFactory factory, Class<T> daoClass){
        if(null==factory){
            return null;
        }
        return getMapper(factory,daoClass,true);
    }

    public static SqlSessionFactory getSessionFactory(){
        return factory;
    }

    public static SqlSessionFactoryBuilder getSqlSessionFactoryBuilder() {
        return sqlSessionFactoryBuilder;
    }
}
