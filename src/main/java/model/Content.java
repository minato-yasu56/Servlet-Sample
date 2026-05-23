package model;

import java.time.LocalDateTime;

public class Content {
	private int id;
	private String text;
	private String postUserId;
	private LocalDateTime postTime;
	private static int len = 0;
	
	public Content(String text, String postUserId) {
		this.id = addContent();
		this.text = text;
		this.postUserId = postUserId;
		postTime = LocalDateTime.now();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPostUserId() {
		return postUserId;
	}
	public void setPostUserId(String postUserId) {
		this.postUserId = postUserId;
	}
	
	public LocalDateTime getPostTime() {
		return postTime;
	}
	
	private static int addContent() {
		len += 1;
		return len;
	}
}
