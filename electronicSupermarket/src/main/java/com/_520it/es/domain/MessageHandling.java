package com._520it.es.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by dell on 2017/7/15.
 */
@Setter@Getter@ToString
public class MessageHandling {
    private Long id;
    private String user;//用户
    private String requestContent;//接收内容
    private String responseContent;//回复内容
    private String infoType;//消息类型
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date infoTime;//消息时间
}
