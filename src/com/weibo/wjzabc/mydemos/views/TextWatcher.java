package com.weibo.wjzabc.mydemos.views;

import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class TextWatcher extends Activity implements android.text.TextWatcher {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textwatcher);
		EditText edit=(EditText)findViewById(R.id.textwatcher_editText1);
		edit.addTextChangedListener(this);
		text=(TextView)findViewById(R.id.textwatcher_Text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_watcher, menu);
		return true;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		text.setText(text.getText()+"\n beforeTextChanged() s="+s);
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		text.setText(text.getText()+"\n onTextChanged() s="+s);
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		text.setText(text.getText()+"\n afterTextChanged()");
	}

}
