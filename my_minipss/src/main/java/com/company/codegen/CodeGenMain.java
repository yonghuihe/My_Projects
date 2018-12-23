package com.company.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CodeGenMain {
	private static Configuration cfg;
	
	static{
		try {
			new CodeGenMain().initFreeMarker();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历传入的参数，识别每一个要生成的代买的domain类型，并封装一个用于具体生成代码的方法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*if (args.length == 1) {
			String clazzStr = args[0];
			// 变成一个Class对象,并使用反射得到这个类的一些基本的信息;
			Class<?> clazz = Class.forName(clazzStr);
			genCode(clazz);
		}*/
		//genCode(SaleAccount.class);
		//System.out.println("生成成功");
	}

	/**
	 * FreeMarker基本运行环境
	 * 
	 * @throws Exception
	 */
	public void initFreeMarker() throws Exception {
		// 创建FreeMarker配置对象
		cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setDefaultEncoding("UTF-8");
		// 配置FreeMarker模板加载器
		File f = new File("src/main/java/com/company/codegen/template");
		cfg.setDirectoryForTemplateLoading(f);
	}

	public static void genCode(Class<?> clazz) throws Exception {
		// 整体的思路
		// 从参数传一个要生成代码的domain的类全限定名;
		// 把搜集到的信息包装到一个对象中(这个对象就是包含了所有模板需要的信息的root对象)
		ClassInfo ci = new ClassInfo(clazz);
		// 完成模板;
		// 1,合成DAO接口;
		genJavaFile(ci, "IDao.ftl", "src/main/java/{0}/dao/I{1}Dao.java");

		// 2,合成DAO实现;
		genJavaFile(ci, "DaoImpl.ftl", "src/main/java/{0}/dao/impl/{1}DaoImpl.java");

		// 3,生成映射文件;
		genJavaFile(ci, "hbm.ftl", "src/main/resources/{0}/domain/{1}.hbm.xml");

		// 4,生成service接口;
		genJavaFile(ci, "IService.ftl", "src/main/java/{0}/service/I{1}Service.java");
		// 5,生成service实现;
		genJavaFile(ci, "ServiceImpl.ftl", "src/main/java/{0}/service/impl/{1}ServiceImpl.java");
		// 6,生成action;
		genJavaFile(ci, "Action.ftl", "src/main/java/{0}/mvc/{1}Action.java");
		// 7,生成list.jsp
		genJavaFile(ci, "list.ftl", "src/main/webapp/WEB-INF/views/{2}/list.jsp");
		// 8,生成input.jsp
		genJavaFile(ci, "input.ftl", "src/main/webapp/WEB-INF/views/{2}/input.jsp");
		// 9,生成对应的配置文件片段,并且合并到对应的配置文件中;
		// 9.1生成dao的配置文件
		mergeXml(ci, "daoxml.ftl", "src/main/resources/applicationContext-dao.xml");
		// 9.2生成service的配置文件
		mergeXml(ci, "servicexml.ftl", "src/main/resources/applicationContext-service.xml");
		// 9.3生成mvc的配置文件
		mergeXml(ci, "mvcxml.ftl", "src/main/resources/applicationContext-mvc.xml");
	}

	private static void mergeXml(ClassInfo ci, String templateName, String targetXml) throws Exception {
		Template template = cfg.getTemplate(templateName);
		StringWriter sw = new StringWriter();
		template.process(ci, sw);
		XmlUtil.mergeXML(new File(targetXml), sw.toString());
	}

	private static void genJavaFile(ClassInfo ci, String templateName, String filePathTemplate) throws Exception {
		Template template = cfg.getTemplate(templateName);
		String uncap = ci.getClassName().substring(0, 1).toLowerCase() + ci.getClassName().substring(1);
		String filePath = MessageFormat.format(filePathTemplate, ci.getBasePackage().replace(".", "/"),
				ci.getClassName(), uncap);
		File f = new File(filePath);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
		}

		template.process(ci, new FileWriter(f));
	}
}
