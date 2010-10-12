package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class MediaRestriction extends basicXMLEntry{
	private String type;
	private String relationship;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Override
	public String toString() {
		return "MediaRestriction [type=" + type + ", relationship="
				+ relationship + "]";
	}
	
	
}	
