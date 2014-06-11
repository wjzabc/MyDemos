package com.weibo.wjzabc.mydemos.savingdata;


import com.weibo.wjzabc.mydemos.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PreferenceCategory extends android.preference.PreferenceCategory{

	public PreferenceCategory(Context context) {
		super(context);
	}
	
	public PreferenceCategory(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PreferenceCategory(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	protected View onCreateView(ViewGroup parent) {
		View view =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.preference_category, null);
		TextView title = (TextView) view.findViewById(R.id.pcategory);
		title.setText(getTitle());
		return view;
	}
	

}
