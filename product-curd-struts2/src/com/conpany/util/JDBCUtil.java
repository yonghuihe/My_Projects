package com.conpany.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 单例模式： 1.私有化构造器 2.单例对象自己创建自己的初始化对象 3.向外界暴露一个方法用来获取自身对象
 * 
 * @author yonghui
 *
 */
public class JDBCUtil {
	private Properties properties = new Properties();
	private DataSource dataSource = null;

	private static final JDBCUtil instance = new JDBCUtil();

	private JDBCUtil() {
		try {
			// 加载注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			properties.load(inStream);
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JDBCUtil getInstance() {
		return instance;
	}

	// 获取连接对象
	public Connection getConn() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(Connection conn ,PreparedStatement ps, ResultSet rs){
		try{
			if (conn != null){
				conn.close();
			}			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (ps != null){
					ps.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				try{
					if (rs != null){
						rs.close();
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
