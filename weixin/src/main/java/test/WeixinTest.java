package test;

import org.junit.Test;

import com.company.wx.util.HttpUtil;
import com.company.wx.util.WeixinUtil;

public class WeixinTest {

	@Test
	public void testCreateMenu() {
		/**
		 * 创建自定义菜单 
		 * 1、在真实项目中，按钮可以做增删改查
		 * 2、将按钮的属性保存到数据库中，当我们更新按钮时，将转换成json，拼接按钮，告诉微信，需要重新去创建菜单
		 * 3、将一级二级菜单保存到数据库中 确认创建： 查询数据库：获取一级、二级菜单 按照微信要求拼接字符串，
		 * 创建自定义菜单（发送POST请求）发送拼接好的字符串（微信会创建自定义菜单）
		 */
		//String codeUrl = WeixinUtil.GET_CODE_URL.replace("REDIRECT_URI", "http://company.s1.natapp.cc/index.do").replace("SCOPE", "snsapi_userinfo");
		//System.out.println("codeUrl:" + codeUrl);
		String result = HttpUtil.post(WeixinUtil.CREATE_MENU_URL.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken()),
				"{ \"button\":[ { \"type\":\"click\", \"name\":\"开班信息\", \"key\":\"classinfo\" }, { \"type\":\"click\", \"name\":\"校区地址\", \"key\":\"address\" }, { \"name\":\"学科介绍\", \"sub_button\":[ { \"type\":\"view\", \"name\":\"Java课程\", \"url\":\"http://www.wolfcode.cn/zt/java/index.html\" }, { \"type\":\"view\", \"name\":\"Python课程\", \"url\":\"http://www.wolfcode.cn/zt/python/index.html\" }] }] }");
		System.out.println(result);
	}
	
	/**
	 * 删除自定义菜单
	 * @throws Exception
	 */
	@Test
	public void deleteMenu() throws Exception {
		String result = HttpUtil.get(WeixinUtil.DELETE_MENU_URL.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken()));
		System.out.println(result);
	}

	@Test
	public void testSendTemplate() throws Exception {
		String result = HttpUtil.post(
				WeixinUtil.POST_SEND_TEMPLATE_URL.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken()),
				"{\"touser\":\"otoxT556xcaf3ntNIWfRay_H27mc\",\"template_id\":\"tO5L2XmL5idFizvWau19ErDc3l7RScVIPN41J642zGA\",\"url\":\"http://www.baidu.com\",\"data\":{ \"first\":{ \"value\":\"恭喜你购买成功！\", \"color\":\"#173177\" }, \"keyword1\":{ \"value\":\"巧克力\", \"color\":\"#173177\" }, \"keyword2\":{ \"value\":\"39.8元\", \"color\":\"#173177\" }, \"keyword3\":{ \"value\":\"2014年9月22日\", \"color\":\"#173177\" }, \"remark\":{ \"value\":\"欢迎再次购买！\", \"color\":\"#173177\" } } }");
		System.out.println(result);
	}

}
