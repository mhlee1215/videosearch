package com.ccmedia.parse.youTube.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class YtDuration extends basicXMLEntry{
	private int seconds;

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	@Override
	public String toString() {
		return "YtDuration [seconds=" + seconds + "]";
	}
	
	
}
