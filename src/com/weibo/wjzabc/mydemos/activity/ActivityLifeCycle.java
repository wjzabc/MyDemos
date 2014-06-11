package com.weibo.wjzabc.mydemos.activity;
import com.weibo.wjzabc.mydemos.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.widget.TextView;

public class ActivityLifeCycle extends Activity {
	private TextView textview;
	@SuppressLint("NewApi")
	@Override
	public void onActionModeFinished(ActionMode mode) {
		textview.setText(textview.getText()+"onActionModeFinished\n");
		super.onActionModeFinished(mode);
	}
	@SuppressLint("NewApi")
	@Override
	public void onActionModeStarted(ActionMode mode) {
		textview.setText(textview.getText()+"onActionModeStarted\n");
		super.onActionModeStarted(mode);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_activitylifecycle);
		textview=(TextView)findViewById(R.id.editText1);
		textview.setText(textview.getText()+"onCreate\n");
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		textview.setText(textview.getText()+"onRestoreInstanceState\n");
		super.onRestoreInstanceState(savedInstanceState);
	}
	@Override
	protected void onRestart() {
		textview.setText(textview.getText()+"onRestart\n");
		super.onRestart();
	}
	@Override
	protected void onStart() {
		textview.setText(textview.getText()+"onStart\n");
		super.onStart();
	}
	@Override
	protected void onResume() {
		textview.setText(textview.getText()+"onResume\n");
		super.onResume();
	}
	@Override
	public void onPanelClosed(int featureId, Menu menu) {
		textview.setText(textview.getText()+"onPanelClosed\n");
		super.onPanelClosed(featureId, menu);
	}
	@Override
	protected void onPause() {
		textview.setText(textview.getText()+"onPause\n");
		super.onPause();
	}
	@Override
	protected void onStop() {
		textview.setText(textview.getText()+"onStop\n");
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		textview.setText(textview.getText()+"onDestroy\n");
		super.onDestroy();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		textview.setText(textview.getText()+"onSaveInstanceState\n");
		super.onSaveInstanceState(outState);
	}

}
