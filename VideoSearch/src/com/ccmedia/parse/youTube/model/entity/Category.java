package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class Category extends basicXMLEntry{
	private String scheme;
	private String term;
	private String label;
	
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "Category [scheme=" + scheme + ", term=" + term + ", label="
				+ label + "]";
	}
	
	
}
