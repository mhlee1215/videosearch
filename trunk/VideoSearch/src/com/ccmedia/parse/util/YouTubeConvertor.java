package com.ccmedia.parse.util;

import java.util.Vector;

import com.ccmedia.ParsedYouTubeDataSet;
import com.ccmedia.parse.model.SearchResultEntity;
import com.ccmedia.parse.model.VideoEntity;
import com.ccmedia.parse.youTube.model.entity.Entry;
import com.ccmedia.parse.youTube.model.entity.Feed;
import com.ccmedia.parse.youTube.model.entity.MediaContent;

public class YouTubeConvertor {
	public static SearchResultEntity convert(ParsedYouTubeDataSet dataSet){
		SearchResultEntity searchResultEntity = new SearchResultEntity();
		
		Feed feed = dataSet.getFeed();
		
		String providerName = "YouTube";
		String title = feed.getTitle().getInnerText();
		String totalResult = feed.getTotalResult().getInnerText();
		String startIndex = feed.getStartIndex().getInnerText();
		String itemsPerPage = feed.getItemsPerPage().getInnerText();
		
		int totalResultInt = 0;
		int startIndexInt = 0;
		int itemsPerPageInt = 0;
		
		try{
			totalResultInt = Integer.parseInt(totalResult);
			startIndexInt = Integer.parseInt(startIndex);
			itemsPerPageInt = Integer.parseInt(itemsPerPage);
		}catch(Exception e){
			
		}
		
		searchResultEntity.setPrividerName(providerName);
		searchResultEntity.setTitle(title);
		searchResultEntity.setTotalResults(totalResultInt);
		searchResultEntity.setStartIndex(startIndexInt);
		searchResultEntity.setItemsPerPage(itemsPerPageInt);
		
		Vector<Entry> entries = feed.getEntry();
		
		System.out.println("entry size: "+entries.size());
		for(Entry entry : entries){
			VideoEntity entity = new VideoEntity();
			
			entity.setTitle(entry.getMediaGroup().getTitle().getInnerText());
			entity.setCategory(entry.getMediaGroup().getMediaCategory().getInnerText());
			entity.setAuthor(entry.getAuthor().getName().getInnerText());
			entity.setDescription(entry.getMediaGroup().getMediaDescription().getInnerText());
			entity.setPlayerUrl(entry.getMediaGroup().getMediaPlayer().getUrl());
			if(entry.getMediaGroup().getMediaThumbnail().size() > 0 )
				entity.setThumbnailUrl(entry.getMediaGroup().getMediaThumbnail().get(0).getUrl());
			
			for(MediaContent content : entry.getMediaGroup().getMediaContent()){
				if(content.getUrl().startsWith("rtsp")){
					entity.setStreamingUrl(content.getUrl());
					break;
				}
			}
			
//			if(entry.getMediaGroup().getMediaContent().size() > 0){
//				
//				entity.setStreamingUrl(entry.getMediaGroup().getMediaContent().get(0).getUrl());
//			}
			
			searchResultEntity.getVideoEntities().add(entity);
		}
		
		return searchResultEntity;
	}
}
