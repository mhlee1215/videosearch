package com.ccmedia.parse.daum.model.entity;

import com.ccmedia.parse.model.basicXMLEntry;

public class Rss extends basicXMLEntry{
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "Rss [channel=" + channel + "]";
	}
	
}
