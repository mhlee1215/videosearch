package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class Author extends basicXMLEntry{
	private basicXMLEntry name;
	private basicXMLEntry uri;
	
	public Author(){
		name = new basicXMLEntry();
		uri = new basicXMLEntry();
	}
	public basicXMLEntry getName() {
		return name;
	}
	public void setName(basicXMLEntry name) {
		this.name = name;
	}
	public basicXMLEntry getUri() {
		return uri;
	}
	public void setUri(basicXMLEntry uri) {
		this.uri = uri;
	}
	
	@Override
	public String toString() {
		return "Author [name=" + name + ", uri=" + uri + "]";
	}
}
