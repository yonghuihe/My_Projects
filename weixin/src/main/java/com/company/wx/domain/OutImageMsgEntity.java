package com.company.wx.domain;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 回复图片消息
 * @author yonghui
 *
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class OutImageMsgEntity extends OutMsgEntity {

	/*
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@XmlElementWrapper(name = "Image")
	private String[] MediaId;

	public String[] getMediaId() {
		return MediaId;
	}

	public void setMediaId(String[] mediaId) {
		MediaId = mediaId;
	}

	@Override
	public String toString() {
		return "OutImageMsgEntity [MediaId=" + Arrays.toString(MediaId) + ", getToUserName()="
				+ getToUserName() + ", getFromUserName()=" + getFromUserName() + ", getCreateTime()=" + getCreateTime()
				+ ", getMsgType()=" + getMsgType() + "]";
	}

}
