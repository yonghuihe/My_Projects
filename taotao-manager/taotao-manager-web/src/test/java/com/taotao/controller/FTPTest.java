package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {

	@Test
	public void testFtpClient() throws Exception {
		//创建FTPClient对象
		FTPClient ftpClient = new FTPClient();
		try {
			//创建ftp连接
			ftpClient.connect("192.168.25.130", 21);
			//登录ftp服务器，使用用户名和密码
			ftpClient.login("ftpuser", "ftpuser");
			//上传文件
			//读取本地文件
			FileInputStream inputStream = new FileInputStream(new File("D:/offen/Pictures/xxx/img.png"));
			//设置上传的路径
			ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
			//修改上传文件的格式
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			//第一个参数：服务器端文档名
			//第二个参数：上传文档的inputStream
			ftpClient.storeFile("z.png", inputStream);
			System.out.println(ftpClient.toString());
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			//关闭连接
			ftpClient.logout();
		}
	}

	@Test
	public void testFtpUtil() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File("D:/offen/Pictures/xxx/img.png"));
		FtpUtil ftpUtil = new FtpUtil();
		ftpUtil.uploadFile("192.168.25.130", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images",
				"/2019/1/11", "zy.jpg", inputStream);
	}
}
