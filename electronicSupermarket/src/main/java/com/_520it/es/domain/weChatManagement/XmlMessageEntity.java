package com._520it.es.domain.weChatManagement;

import com._520it.es.domain.Item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * 接收和回复消息的domian
 * @author Administrator
 *
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlMessageEntity {

	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private String MsgType;
	private String Content;
	private String Event;
	private Long MsgId;

	//图文部分
	private Integer ArticleCount;
	private List<Item> Articles = new ArrayList<>();

	public String getEvent() {
		return Event;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}

	public List<Item> getArticles() {
		return Articles;
	}

	public void setArticles(List<Item> articles) {
		Articles = articles;
	}

	@Override
	public String toString() {
		return "XmlMessageEntity [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", Event=" + Event + ", MsgType=" + MsgType
				+ ", Content=" + Content + ", MsgId=" + MsgId + "]";
	}

}
