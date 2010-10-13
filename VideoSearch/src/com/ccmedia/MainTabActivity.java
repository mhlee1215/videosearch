package com.ccmedia;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTabActivity extends TabActivity {

	TabHost host;
	 
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
        finish();
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main_tab);
		
		Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, TagCloudActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Keywords").setIndicator("Tags",
	                      res.getDrawable(R.drawable.tab_menu_1))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, VideoSearchActivity.class);
	    spec = tabHost.newTabSpec("Search").setIndicator("Search",
	                      res.getDrawable(R.drawable.tab_menu_2))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, VideoSearchActivity.class);
	    spec = tabHost.newTabSpec("View").setIndicator("Multi View",
	                      res.getDrawable(R.drawable.tab_menu_3))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	    host = tabHost;
	    AppObject.main = this;
	}
	
	public TabHost getHost() {
		return host;
	}
}