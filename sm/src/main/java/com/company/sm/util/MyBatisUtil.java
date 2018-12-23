package com.company.sm.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sessionFactory;
	private MyBatisUtil(){};
	
	static{
		try {
			// 创建SessionFactory
			sessionFactory = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsStream("mybatis.cfg.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession openSession() {
		return sessionFactory.openSession();
	}
}
