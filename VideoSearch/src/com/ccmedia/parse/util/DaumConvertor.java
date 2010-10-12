package com.ccmedia.parse.util;

import java.util.Vector;

import com.ccmedia.ParsedDaumDataSet;
import com.ccmedia.parse.daum.model.entity.Channel;
import com.ccmedia.parse.daum.model.entity.Item;
import com.ccmedia.parse.daum.model.entity.Rss;
import com.ccmedia.parse.model.SearchResultEntity;
import com.ccmedia.parse.model.VideoEntity;

public class DaumConvertor {
	public static SearchResultEntity convert(ParsedDaumDataSet dataSet){
		SearchResultEntity searchResultEntity = new SearchResultEntity();
		
		Rss rss = dataSet.getRss();
		Channel channel = rss.getChannel();
		
		String providerName = "Daum";
		String title = channel.getTitle().getInnerText();
		String totalResult = channel.getTotalCount().getInnerText();
		String startPageno = channel.getPageno().getInnerText();
		String itemsPerPage = channel.getResult().getInnerText();
		
		int totalResultInt = 0;
		int startPagenoInt = 0;
		int itemsPerPageInt = 0;
		
		try{
			totalResultInt = Integer.parseInt(totalResult);
			startPagenoInt = Integer.parseInt(startPageno);
			itemsPerPageInt = Integer.parseInt(itemsPerPage);
		}catch(Exception e){
			
		}
		
		searchResultEntity.setPrividerName(providerName);
		searchResultEntity.setTitle(title);
		searchResultEntity.setTotalResults(totalResultInt);
		searchResultEntity.setStartIndex(startPagenoInt);
		searchResultEntity.setItemsPerPage(itemsPerPageInt);
		
		Vector<Item> items = channel.getItem();
		
		System.out.println("items size: "+items.size());
		for(Item entry : items){
			VideoEntity entity = new VideoEntity();
			
//			entity.setTitle(entry.getMediaGroup().getTitle().getInnerText());
//			entity.setCategory(entry.getMediaGroup().getMediaCategory().getInnerText());
//			entity.setAuthor(entry.getAuthor().getName().getInnerText());
//			entity.setDescription(entry.getMediaGroup().getMediaDescription().getInnerText());
//			entity.setPlayerUrl(entry.getMediaGroup().getMediaPlayer().getUrl());
//			if(entry.getMediaGroup().getMediaThumbnail().size() > 0 )
//				entity.setThumbnailUrl(entry.getMediaGroup().getMediaThumbnail().get(0).getUrl());
//			
//			for(MediaContent content : entry.getMediaGroup().getMediaContent()){
//				if(content.getUrl().startsWith("rtsp")){
//					entity.setStreamingUrl(content.getUrl());
//					break;
//				}
//			}
			
//			if(entry.getMediaGroup().getMediaContent().size() > 0){
//				
//				entity.setStreamingUrl(entry.getMediaGroup().getMediaContent().get(0).getUrl());
//			}
			
			searchResultEntity.getVideoEntities().add(entity);
		}
		
		return searchResultEntity;
	}
}
