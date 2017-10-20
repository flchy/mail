package com.flchy.blog.plugin.entity;

import java.io.Serializable;

/**
 * 邮件消息实体
 * 
 * @author flchy
 *
 */
public class Mail implements Serializable {
	private static final long serialVersionUID = 4575491021109972512L;

	private String title;
	private String content;
	private String to;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Mail [title=" + title + ", content=" + content + ", to=" + to + "]";
	}
	
	

}
