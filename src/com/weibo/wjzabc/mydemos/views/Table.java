package com.weibo.wjzabc.mydemos.views;

import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TabHost;

public class Table extends  TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ArtistsActivity.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("artist")
				.setIndicator("Artistss",
						res.getDrawable(R.drawable.ic_launcher))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, AlbumsActivity.class);
		spec = tabHost
				.newTabSpec("album")
				.setIndicator("Albumss", res.getDrawable(R.drawable.ic_launcher))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SongsActivity.class);
		spec = tabHost.newTabSpec("song")
				.setIndicator("Songss", res.getDrawable(R.drawable.ic_launcher))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.table, menu);
		return true;
	}

}
