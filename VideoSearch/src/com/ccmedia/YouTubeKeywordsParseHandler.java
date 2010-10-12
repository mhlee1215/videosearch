package com.ccmedia;

import java.util.Stack;

import org.mcavallo.opencloud.Cloud;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ccmedia.parse.model.basicXMLEntry;
import com.ccmedia.parse.youTube.model.entity.Author;
import com.ccmedia.parse.youTube.model.entity.Category;
import com.ccmedia.parse.youTube.model.entity.Entry;
import com.ccmedia.parse.youTube.model.entity.Feed;
import com.ccmedia.parse.youTube.model.entity.GdComments;
import com.ccmedia.parse.youTube.model.entity.GdFeedLink;
import com.ccmedia.parse.youTube.model.entity.GdRating;
import com.ccmedia.parse.youTube.model.entity.Generator;
import com.ccmedia.parse.youTube.model.entity.Link;
import com.ccmedia.parse.youTube.model.entity.MediaCategory;
import com.ccmedia.parse.youTube.model.entity.MediaContent;
import com.ccmedia.parse.youTube.model.entity.MediaDescription;
import com.ccmedia.parse.youTube.model.entity.MediaGroup;
import com.ccmedia.parse.youTube.model.entity.MediaPlayer;
import com.ccmedia.parse.youTube.model.entity.MediaRestriction;
import com.ccmedia.parse.youTube.model.entity.MediaThumbnail;
import com.ccmedia.parse.youTube.model.entity.Title;
import com.ccmedia.parse.youTube.model.entity.YtDuration;
import com.ccmedia.parse.youTube.model.entity.YtStatistics;

public class YouTubeKeywordsParseHandler extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================

	private Cloud cloud = null;
	private boolean in_keywordstag = false;

	private ParsedYouTubeDataSet myParsedExampleDataSet = new ParsedYouTubeDataSet();

	public YouTubeKeywordsParseHandler(int maxTags){
		this.cloud = new Cloud();
		cloud.setMaxTagsToDisplay(maxTags);
	}
	
	public YouTubeKeywordsParseHandler(Cloud cloud){
		this.cloud = cloud;
		cloud.setMaxTagsToDisplay(20);
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ParsedYouTubeDataSet getParsedData() {
		return this.myParsedExampleDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.myParsedExampleDataSet = new ParsedYouTubeDataSet();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
		System.out.println(myParsedExampleDataSet);
	}

	/**
	 * Gets be called on opening tags like: <tag> Can provide attribute(s), when
	 * xml was like: <tag attribute="attributeValue">
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equals("keywords")) {
			in_keywordstag = true;
		} else {
			in_keywordstag = false;
		}
		
	}

	/**
	 * Gets be called on closing tags like: </tag>
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

	}

	/**
	 * Gets be called on the following structure: <tag>characters</tag>
	 */
	@Override
	public void characters(char ch[], int start, int length) {
		//System.out.println("char..." + new String(ch, start, length));
		if(in_keywordstag){
			String keywordsStr = new String(ch, start, length);
			String[] keywordsPart = keywordsStr.split(",");
			for(String keyword : keywordsPart){
				//System.out.println("addKeyword: "+keyword.trim());
				cloud.addText(keyword.trim());
			}
		}
	}
	
	public Cloud getCloud(){
		return this.cloud;
	}
}