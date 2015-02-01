/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.columbia.mysqltomongodbconv.mybatis.util;

import java.io.InputStream;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author saikarthikreddyginni
 */
public class MyBatisConnectionFactory {

    private static final SqlSessionFactory factory;

    private MyBatisConnectionFactory() {
    }

    static {
        InputStream inputStream = null;
        try {
            inputStream = MyBatisConnectionFactory.class.getClassLoader().getResourceAsStream("mybatis-configuration.xml");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }
}
