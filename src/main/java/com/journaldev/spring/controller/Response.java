package com.journaldev.spring.controller;

import java.util.List;

public class Response<T> {
	
	private String message;
	
	private String statusCode;
	
	 protected List<T> content;
	 
     private T data;
     
     private long count;
     
     private long totalNumFound;
     
     private List<ApiError> apiErrors;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getTotalNumFound() {
		return totalNumFound;
	}

	public void setTotalNumFound(long totalNumFound) {
		this.totalNumFound = totalNumFound;
	}

	public List<ApiError> getApiErrors() {
		return apiErrors;
	}

	public void setApiErrors(List<ApiError> apiErrors) {
		this.apiErrors = apiErrors;
	}
     
     
     

	

}
