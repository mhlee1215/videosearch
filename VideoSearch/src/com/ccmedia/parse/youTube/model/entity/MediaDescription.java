package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class MediaDescription extends basicXMLEntry{
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MediaDescription [type=" + type + "]";
	}
	
	
}
