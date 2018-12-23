package com._520it.es.web.controller;

import com._520it.es.domain.Article;
import com._520it.es.domain.Item;
import com._520it.es.domain.KeywordReply;
import com._520it.es.domain.UserInfo;
import com._520it.es.domain.weChatManagement.XmlMessageEntity;
import com._520it.es.service.IKeywordReplyService;
import com._520it.es.service.IUserInfoService;
import com._520it.es.util.SecurityUtil;
import com._520it.es.util.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 接入url并验证
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	@Autowired
    IKeywordReplyService keywordReplyService;
	@Autowired
	IUserInfoService userInfoService;

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "index";
	}

	// 验证url
	@RequestMapping(value="/supermarket",method=RequestMethod.GET)
	@ResponseBody
	public String validate(String signature, String timestamp, String nonce, String echostr){
		// 如果不是通过微信验证url，即signature为null就不处理直接返回null
		if(signature != null){
			// 验证URL
			String[] arr = {WeiXinUtil.TOKEN, timestamp, nonce};
			Arrays.sort(arr);
			String result="";
			for(String e:arr){
				result+=e;
			}
			String sha1 = SecurityUtil.SHA1(result);
			if(sha1.equals(signature)){
				System.out.println("-----------验证成功---------");
				return echostr;
			}
			System.out.println("-----------验证失败---------");
			}
		System.out.println("---结束---");
		return null;
	}
	
	/**
	 * 接收用户的消息,并在关注的时候回复
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/supermarket",method=RequestMethod.POST)
	@ResponseBody
	public XmlMessageEntity handleMsg(@RequestBody XmlMessageEntity entity){
		UserInfo userInfo = new UserInfo();
		List<String> listKeywords = new ArrayList<>();
		List<String> listResponse = new ArrayList<>();
		List<KeywordReply> keywordReplies = keywordReplyService.selectReply();
		for (KeywordReply keywordReply : keywordReplies) {
			listKeywords.add(keywordReply.getKeywords());
			listResponse.add(keywordReply.getResponseContent());
		}

		System.out.println("接受消息！！！！");

		XmlMessageEntity newEntity = new XmlMessageEntity();
		//设置接受方
		newEntity.setToUserName(entity.getFromUserName());
		//设置发送方
		newEntity.setFromUserName(entity.getToUserName());
		//设置返回类型
		newEntity.setMsgType("text");
		//设置响应时间
		newEntity.setCreateTime(new Date().getTime());
		if ("event".equals(entity.getMsgType())) {
			//如果第一次关注，就回复：欢迎关注
			if ("subscribe".equals(entity.getEvent())) {
				userInfo.setNickname(entity.getFromUserName());
				userInfoService.insert(userInfo);
				newEntity.setContent("终于等到你,还好我没放弃。" +
						"❀感谢您关注了这么一个:" +
						"器不粗，活不好，没有干爹，" +
						"有颜值，还不任性的账号。" +
						"真实辛苦您了❤❤" +
						"为此，给大家带来一份神秘的礼物。请直接回复数字:☞1~99☜");
				return handler(entity);
			} else {
				//如果是取消关注，更新粉丝状态
				System.out.println("更新粉丝状态为取消关注");
			}
		}
		if ("CLICK".equals(entity.getEvent())) {
			newEntity.setContent("请直接回复数字：☞1~10☜" +
					"\n✔   回复 1:     自助服务    " +
					"\n✔   回复 2:     业务咨询    " +
					"\n✔   回复 3:     售后服务    " +
					"\n✔   回复 4:     销售品牌    " +
					"\n✔   回复 5:     综合服务    " +
					"\n✔   回复 6:     其他:        " +
					"\n✈   请回复数字:  ☞7~10☜" +
					"\n服务热线:400-888-88889" +
					"\n<a href='http://2d62vn.natappfree.cc/index.do'>商城快速入口>>></a>");

		}
		if ("text".equals(entity.getMsgType())){
			newEntity.setContent("终于等到你,还好我没放弃。" +
					"\n❀感谢您关注了这么一个:" +
					"\n❀器不粗，活不好，没有干爹，" +
					"\n❀有颜值，还不任性的账号。" +
					"\n❀真实辛苦您了❤❤" +
					"\n❀为此，给大家带来一份神秘的礼物。" +
					"\n❀请直接回复数字:☞1~10☜");
			for (int i = 0; i < listKeywords.size(); i++){
				if (listKeywords.get(i).equals(entity.getContent())){
					newEntity.setContent(listResponse.get(i));
				}
			}
			/*if ("1".equals(entity.getContent())){
				newEntity.setContent("别紧张,我又不是什么好人✈……");
			}else if ("2".equals(entity.getContent())){
				newEntity.setContent("最近总是失眠，16小时就醒一次");
			}else if ("3".equals(entity.getContent())){
				newEntity.setContent("人人都说我丑，其实我只是美得不明显");
			}else if ("4".equals(entity.getContent())){
				newEntity.setContent("我从不以强凌弱～～～我欺负他之前真不知道他比我弱…");
			}else if ("5".equals(entity.getContent())){
				newEntity.setContent("你为什么不找个安静的地方自个儿数数脑细胞儿去?");
			}else if ("6".equals(entity.getContent())){
				newEntity.setContent("人生只有三天，活在昨天的人迷惑;活在明天的人等候;活在今天的人最踏实。");
			}else if ("7".equals(entity.getContent())){
				newEntity.setContent("通往成功的路，总是在施工中。");
			}else if ("8".equals(entity.getContent())){
				newEntity.setContent("其实，我由衷地想把赚钱变成我的业余爱好。");
			}else if ("9".equals(entity.getContent())){
				newEntity.setContent("女子无才便是德，前面的哪个女子一定是太缺德了。");
			}else if ("10".equals(entity.getContent())){
				newEntity.setContent("身穿地摊货，脚蹬温州鞋，全身上下加起来不超过200元，只有手里的包算是高级皮包，因为它的英文名就叫“Gaojipibao”。");
			}*/

		}
		System.out.println(entity);
		return newEntity;
	}

	private XmlMessageEntity handler(XmlMessageEntity entity){
		XmlMessageEntity newEntity = new XmlMessageEntity();
		List<Item> list = newEntity.getArticles();

		//设置接受方
		newEntity.setToUserName(entity.getFromUserName());
		//设置发送方
		newEntity.setFromUserName(entity.getToUserName());
		//设置响应时间
		newEntity.setCreateTime(new Date().getTime());
		//设置返回类型
		newEntity.setMsgType("news");
		newEntity.setArticleCount(1);

		Article article= new Article();
		article.setTitle("✌2017年年中最热✌");
		article.setDescription("最火热的商品");
		article.setPicUrl("http://pic.58pic.com/58pic/13/55/94/41q58PICRTe_1024.jpg");
		article.setUrl("http://2d62vn.natappfree.cc/index.do");

		Item item = new Item();
		item.setItem(article);
		List<Item> items = new ArrayList<>();
		items.add(item);

		newEntity.setArticles(items);

		return newEntity;
	}
}
