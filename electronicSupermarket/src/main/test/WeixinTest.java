import org.junit.Test;

import com._520it.es.util.HttpUtil;
import com._520it.es.util.WeiXinUtil;

public class WeixinTest {

    @Test
    public void testMenu() throws Exception {
        String post = HttpUtil.post(WeiXinUtil.CREATE_MENU_URL.replace("ACCESS_TOKEN",WeiXinUtil.getAccessToken()),"{\"button\":[{\"name\":\"人气产品\",\"sub_button\":[{\"type\":\"view\",\"name\":\"电脑\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"鼠标\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"键盘\",\"url\":\"http://www.soso.com/\"}]},\t\t{\t\t\t\t\"type\":\"click\",\t\t\t\t\"name\":\"即刻购买\",\t\t\t\t\"key\":\"click_shop\"\t\t},{\"name\":\"售后服务\",\"sub_button\":[{\"type\":\"view\",\"name\":\"微官网\",\"url\":\"http://www.soso.com/\"},{\"type\":\"click\",\"name\":\"服务热线\",\"key\":\"click_tel\"},{\"type\":\"click\",\"name\":\"周五福利\",\"key\":\"click_gift\"}]}]}");
        System.out.println(post);
    }
}
