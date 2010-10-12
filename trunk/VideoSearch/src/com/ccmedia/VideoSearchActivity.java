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

public class VideoSearchActivity extends Activity {

	/**
	 * The name of the server hosting the YouTube GDATA feeds
	 */
	public static final String YOUTUBE_GDATA_SERVER = "http://gdata.youtube.com";
	/**
	 * The URL of the "Videos" feed
	 */
	public static final String VIDEOS_FEED = YOUTUBE_GDATA_SERVER
			+ "/feeds/api/videos";

	private final String MY_DEBUG_TAG = "WeatherForcaster";

	EditText searchQueryText = null;
	Button searchButton = null;
	ListView resultView = null;
	
	SearchResultAdapter searchAdapter;

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		
		if(AppObject.reservedSearchKeyword != null && !"".equals(AppObject.reservedSearchKeyword)){
			goSearch(AppObject.reservedSearchKeyword);
			AppObject.reservedSearchKeyword = "";
		}
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.video_search);
		/* Create a new TextView to display the parsingresult later. */
		// TextView tv = new TextView(this);
		System.out.println("VideoSearch Created!");
		searchButton = (Button) findViewById(R.id.search_button);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String queryString = searchQueryText.getText().toString();
				System.out.println("click!.."
						+ searchQueryText.getText().toString());
				
				goSearch(queryString);
			}
		});

		searchQueryText = (EditText) findViewById(R.id.searchQueryText);
		resultView = (ListView) findViewById(R.id.search_list);
		
		
		
	}

	public void goSearch(String keyword) {
		searchQueryText.setText(keyword);
		try {

			String youTubeUrl = VIDEOS_FEED + "?q=" + keyword + "&key="
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
			YouTubeParseHandler myExampleHandler = new YouTubeParseHandler();
			xr.setContentHandler(myExampleHandler);

			/* Parse the xml-data from our URL. */
			xr.parse(new InputSource(url.openStream()));
			/* Parsing has finished. */

			/* Our ExampleHandler now provides the parsed data to us. */
			ParsedYouTubeDataSet parsedExampleDataSet = myExampleHandler
					.getParsedData();

			/* Set the result to be displayed in our GUI. */

			// tv.setText(parsedExampleDataSet.toString());

			// tv.setText(YouTubeConvertor.convert(parsedExampleDataSet).toString());

			SearchResultEntity resultEntity = YouTubeConvertor.convert(parsedExampleDataSet);
			Vector<VideoEntity> videoEntity = resultEntity.getVideoEntities();
			ArrayList<VideoEntity> videoEntityArray = new ArrayList<VideoEntity>();
			
			for(VideoEntity ve : videoEntity){
				videoEntityArray.add(ve);
			}
			
			searchAdapter = new SearchResultAdapter(
					this, R.layout.video_search_result_row, videoEntityArray, this, getResources());
			
			resultView.setAdapter(searchAdapter);
			resultView.setOnItemClickListener(new menuClickListener(searchAdapter));
		} catch (Exception e) {
			/* Display any Error to the GUI. */
			// tv.setText("Error: " + e.getMessage());
			Log.e(MY_DEBUG_TAG, "WeatherQueryError", e);
		}
		/* Display the TextView. */
		// this.setContentView(tv);
	}
	

	public class menuClickListener implements OnItemClickListener {

		private SearchResultAdapter adapter;

		public menuClickListener(SearchResultAdapter adapter) {
			this.adapter = adapter;
		}

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			adapter.setSelectedPosition(position);
			VideoEntity videoItem = adapter.getSelectedItem();
			System.out.println(videoItem);
			
			AppObject.streamingUrl = videoItem.getStreamingUrl();
			System.out.println("streamingUrl: "+AppObject.streamingUrl);
			
			Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibe.vibrate(AppObject.vibeTime);    
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(),
					VideoViewActivity.class);
			startActivity(intent);
		}
	}
}