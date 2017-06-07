package com.journaldev.spring.model;

public class RequestBodyText {
	private String text;
    private String conversationId;
    
    private String lang;
    
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getConversationId() {
		return conversationId;
	}
	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
    
    

}
