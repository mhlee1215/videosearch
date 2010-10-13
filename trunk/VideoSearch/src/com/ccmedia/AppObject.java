package com.ccmedia;

import org.mcavallo.opencloud.Cloud;

import android.app.Activity;

public class AppObject {
	public static final String TAG_NAME = "cloud_tag";
	public static final String GOOGLE_DEVELOPER_ID = "mhlee1215";
	public static final String GOOGLE_DEVELOPER_KEY = "AI39si66vcrS6FHxguNi99XNjlDtdzRlYKoalPx-IPG-J3ZtNMcyqVM5KhFDXiFKzi4WtIXWrNsFIe5aJXzum_3F4FF4cQXAxQ";
	public static final String DAUM_DEVELOPER_KEY = "67b4d772600675982a5405aed7649fd069f7ab80";
	public static int vibeTime = 100; 
	public static String streamingUrl = "";
	
	public static Activity main = null;
	public static String reservedSearchKeyword = null;
	
	public static Cloud tagCloud = null;

	public static int getVibeTime() {
		return vibeTime;
	}

	public static void setVibeTime(int vibeTime) {
		AppObject.vibeTime = vibeTime;
	}

	public static String getStreamingUrl() {
		return streamingUrl;
	}

	public static void setStreamingUrl(String streamingUrl) {
		AppObject.streamingUrl = streamingUrl;
	}

	public static Activity getMain() {
		return main;
	}

	public static void setMain(Activity main) {
		AppObject.main = main;
	}

	public static String getReservedSearchKeyword() {
		return reservedSearchKeyword;
	}

	public static void setReservedSearchKeyword(String reservedSearchKeyword) {
		AppObject.reservedSearchKeyword = reservedSearchKeyword;
	}

	public static Cloud getTagCloud() {
		return tagCloud;
	}

	public static void setTagCloud(Cloud tagCloud) {
		AppObject.tagCloud = tagCloud;
	}
	
	
}
