
package com.weibo.wjzabc.mydemos.savingdata;

import com.weibo.wjzabc.mydemos.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class PreferencesInXML extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName("aaa");

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        preferenceScreen.getPreference(0).setEnabled(false);
        preferenceScreen.getPreference(1).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            
            public boolean onPreferenceClick(Preference preference) {
                return false;
            }
        });

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // TODO Auto-generated method stub
        
    }

}
