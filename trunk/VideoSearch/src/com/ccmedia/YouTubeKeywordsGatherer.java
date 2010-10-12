package com.ccmedia;

import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.mcavallo.opencloud.Cloud;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.ccmedia.parse.model.SearchResultEntity;
import com.ccmedia.parse.model.VideoEntity;
import com.ccmedia.parse.util.YouTubeConvertor;

public class YouTubeKeywordsGatherer {
	/**
	 * The name of the server hosting the YouTube GDATA feeds
	 */
	public static final String YOUTUBE_GDATA_SERVER = "http://gdata.youtube.com";
	/**
	 * The URL of the "Videos" feed
	 */
	public static final String VIDEOS_FEED = YOUTUBE_GDATA_SERVER
			+ "/feeds/api/videos";
	
	public static final String VIDEO_TOP_RATED = YOUTUBE_GDATA_SERVER + "/feeds/api/standardfeeds/top_rated";
	public static final String VIDEO_MOST_VIEWED = YOUTUBE_GDATA_SERVER + "/feeds/api/standardfeeds/most_viewed";

	public Cloud goSearch(int maxTagSize) {
		try {

			String youTubeUrl = VIDEO_TOP_RATED + "?key="
					+ AppObject.GOOGLE_DEVELOPER_KEY;

			/* Create a URL we want to load some xml-data from. */
			URL url = null;
			url = new URL(youTubeUrl);

			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();
			/* Create a new ContentHandler and apply it to the XML-Reader */
			YouTubeKeywordsParseHandler parseHandler = new YouTubeKeywordsParseHandler(maxTagSize);
			xr.setContentHandler(parseHandler);

			/* Parse the xml-data from our URL. */
			xr.parse(new InputSource(url.openStream()));
			/* Parsing has finished. */

			return parseHandler.getCloud();
			
		} catch (Exception e) {
			/* Display any Error to the GUI. */
			// tv.setText("Error: " + e.getMessage());
			return null;
		}
		/* Display the TextView. */
		// this.setContentView(tv);
	}
	
	
}
