package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class AppControl extends basicXMLEntry{
	private YtState ytState;

	public AppControl(){
		ytState = new YtState();
	}
	
	public YtState getYtState() {
		return ytState;
	}

	public void setYtState(YtState ytState) {
		this.ytState = ytState;
	}

	@Override
	public String toString() {
		return "AppControl [ytState=" + ytState + "]";
	}
	
	
}	
