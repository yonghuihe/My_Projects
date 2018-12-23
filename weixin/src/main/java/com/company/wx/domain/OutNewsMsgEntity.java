package com.company.wx.domain;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class OutNewsMsgEntity extends OutMsgEntity {

	/**
	 * 图文消息个数； 当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息； 其余场景最多可回复8条图文消息
	 */
	private Integer ArticleCount;

	/**
	 * 图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
	 */
	@XmlElementWrapper(name = "Articles")
	private ArticleItem[] item;

	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}

	public ArticleItem[] getItem() {
		return item;
	}

	public void setItem(ArticleItem[] item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "OutNewsMsgEntity [ArticleCount=" + ArticleCount + ", item=" + Arrays.toString(item)
				+ ", getToUserName()=" + getToUserName() + ", getFromUserName()=" + getFromUserName()
				+ ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType() + "]";
	}

}
