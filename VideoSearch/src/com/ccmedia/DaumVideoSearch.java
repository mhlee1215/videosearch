package com.ccmedia;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.ccmedia.parse.model.SearchResultAdapter;
import com.ccmedia.parse.model.SearchResultEntity;
import com.ccmedia.parse.model.VideoEntity;
import com.ccmedia.parse.util.DaumConvertor;
import com.ccmedia.parse.util.YouTubeConvertor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DaumVideoSearch {

	/**
	 * The name of the server hosting the YouTube GDATA feeds
	 */
	public static final String DAUM_API_SERVER = "http://apis.daum.net";
	/**
	 * The URL of the "Videos" feed
	 */
	public static final String VIDEOS_FEED = DAUM_API_SERVER
			+ "/search/vclip";

	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle icicle) {
//		super.onCreate(icicle);
//		setContentView(R.layout.video_search);
//		/* Create a new TextView to display the parsingresult later. */
//		// TextView tv = new TextView(this);
//		System.out.println("VideoSearch Created!");
//		searchButton = (Button) findViewById(R.id.search_button);
//		searchButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				String queryString = searchQueryText.getText().toString();
//				System.out.println("click!.."
//						+ searchQueryText.getText().toString());
//				
//				goSearch(queryString);
//			}
//		});
//
//		searchQueryText = (EditText) findViewById(R.id.searchQueryText);
//		resultView = (ListView) findViewById(R.id.search_list);
//		
//		
//		
//	}

	public ArrayList<VideoEntity> goSearch(String keyword) {
		try {

			String youTubeUrl = VIDEOS_FEED + "?q=" + keyword + "&key="
					+ AppObject.DAUM_DEVELOPER_KEY;

			/* Create a URL we want to load some xml-data from. */
			URL url = null;
			url = new URL(youTubeUrl);

			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();
			/* Create a new ContentHandler and apply it to the XML-Reader */
			DaumParseHandler myExampleHandler = new DaumParseHandler();
			xr.setContentHandler(myExampleHandler);

			/* Parse the xml-data from our URL. */
			xr.parse(new InputSource(url.openStream()));
			/* Parsing has finished. */

			/* Our ExampleHandler now provides the parsed data to us. */
			ParsedDaumDataSet parsedExampleDataSet = myExampleHandler
					.getParsedData();

			/* Set the result to be displayed in our GUI. */

			// tv.setText(parsedExampleDataSet.toString());

			// tv.setText(YouTubeConvertor.convert(parsedExampleDataSet).toString());

			SearchResultEntity resultEntity = DaumConvertor.convert(parsedExampleDataSet);
			Vector<VideoEntity> videoEntity = resultEntity.getVideoEntities();
			ArrayList<VideoEntity> videoEntityArray = new ArrayList<VideoEntity>();
			
			for(VideoEntity ve : videoEntity){
				videoEntityArray.add(ve);
			}
			return videoEntityArray;
			
		} catch (Exception e) {
			/* Display any Error to the GUI. */
			// tv.setText("Error: " + e.getMessage());
			return null;
		}
		/* Display the TextView. */
		// this.setContentView(tv);
	}
}