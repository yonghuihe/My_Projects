package com.taotao.controller;

public class FTPTest {

	/*@Test
	public void testFtpClient() throws Exception {
		//创建FTPClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接
		ftpClient.connect("192.168.159.135", 21);
		//登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "heyonghuiftpuser");
		//上传文件
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:/Users/dell/Pictures/Captures/yuema.jpeg"));
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务器端文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("hello1.jpg", inputStream);
		//关闭连接
		ftpClient.logout();
	}*/

	/*@Test
	public void testFtpUtil() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File("C:/Users/dell/Pictures/Captures/yuema.jpeg"));
		FtpUtil ftpUtil = new FtpUtil();
		ftpUtil.uploadFile("192.168.159.135", 21, "ftpuser", "heyonghuiftpuser", "/home/ftpuser/www/images",
				"/2017-9-7", "hello.jpg", inputStream);
	}*/
}
