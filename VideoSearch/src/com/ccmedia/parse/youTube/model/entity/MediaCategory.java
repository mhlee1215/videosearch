package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class MediaCategory extends basicXMLEntry{
	private String label;
	private String scheme;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	@Override
	public String toString() {
		return "MediaCategory [label=" + label + ", scheme=" + scheme + "]";
	}
	
	
}
