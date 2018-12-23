package com.conpany.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.conpany.handler.IResultSetHandler;
import com.conpany.util.JDBCUtil;

public class JDBCTemplate<T> {

	public static void update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 获取连接对象
			conn = JDBCUtil.getInstance().getConn();
			// 创建语句结构
			ps = conn.prepareStatement(sql);
			// 执行SQL语句
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtil.getInstance().close(conn, ps, null);
		}
	}
	
	public static <T> T query(String sql,IResultSetHandler<T> T, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 获取连接对象
			conn = JDBCUtil.getInstance().getConn();
			// 创建语句结构
			ps = conn.prepareStatement(sql);
			// 执行SQL语句
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			return (T) T.handler(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtil.getInstance().close(conn, ps, null);
		}
		return null;
	}
}
