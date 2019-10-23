package com.turing.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @Description: TODO(������)  
* @author YangTianHong
* @date 2019��7��24�� ����8:40:20
 */
public class MybatisUtil {
	
	private static MybatisUtil instance = new MybatisUtil();
	private SqlSessionFactory factory = null;
	private  MybatisUtil() {
		try {
			factory = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsStream("mybatis-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSession openSession() {
		return instance.factory.openSession();
	}
}
