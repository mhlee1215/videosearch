package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class YtState extends basicXMLEntry{
	private String name;
	private String reasonCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	@Override
	public String toString() {
		return "YtState [name=" + name + ", reasonCode=" + reasonCode + "]";
	}
	
	
}
