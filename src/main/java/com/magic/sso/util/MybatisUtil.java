package com.magic.sso.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;


public class MybatisUtil {

    private static final SqlSessionFactoryBuilder sqlSessionFactoryBuilder=
            new SqlSessionFactoryBuilder();

    private static SqlSessionFactory factory;

    static {
        try {
            factory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis/mybatis.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  <T> T getMapper(SqlSession sqlSession, Class<T> tClass){
        if(null==factory){
            return null;
        }
        return sqlSession.getMapper(tClass);
    }

    public static SqlSessionFactory getSessionFactory(){
        return factory;
    }

    public static SqlSession getSqlSession(){
        return factory.openSession();
    }

    public static SqlSession getSqlSession(boolean auto){
        return factory.openSession(auto);
    }

    public static SqlSessionFactoryBuilder getSqlSessionFactoryBuilder() {
        return sqlSessionFactoryBuilder;
    }
}
