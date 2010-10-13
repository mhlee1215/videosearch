package com.ccmedia;

import org.mcavallo.opencloud.Cloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TitleActivity extends Activity {

	public final static int INITIALIAING_FINISH = 1;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.title);
		
		InitializingThread initThread = new InitializingThread(handler);
		initThread.start();

		
	}
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			int id = msg.getData().getInt("id");
			
			switch (id) {
			case INITIALIAING_FINISH:
				//Change Activity.
				Intent intent = new Intent();
		        intent.setClass(getApplicationContext(), MainTabActivity.class);
				startActivity(intent);   
				break;
		}
	};
	};
	
	private class InitializingThread extends Thread {
		Handler handler;
		int maxSec = 15;
		public InitializingThread(Handler handler){
			this.handler = handler;
		}
		
		public void run() {
			Log.v(AppObject.TAG_NAME, "Load cloud keyword from youtube..");
			
			YouTubeKeywordsGatherer gatherer = new YouTubeKeywordsGatherer();
			Cloud cloud = gatherer.goSearch(30);
			AppObject.setTagCloud(cloud);
			
			Message msg = handler.obtainMessage();
			Bundle b = new Bundle();
			b.putInt("id", INITIALIAING_FINISH);
			msg.setData(b);
			handler.sendMessage(msg);
				
		}
	}

}