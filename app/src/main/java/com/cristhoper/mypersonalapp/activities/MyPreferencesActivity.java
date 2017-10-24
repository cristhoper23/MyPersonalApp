package com.cristhoper.mypersonalapp.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cristhoper.mypersonalapp.R;

import im.delight.android.languages.Language;
import im.delight.android.languages.LanguageList;

public class MyPreferencesActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        Log.d("settings", "preference changed: " + s);
        if("username".equals(s)){
            Log.d("settings", "new value for username: " + sharedPreferences.getString(s, null));
        }else if("applicationUpdates".equals(s)){
            Log.d("settings", "new value for applicationUpdates: " + sharedPreferences.getBoolean(s, false));
        }else if("downloadType".equals(s)){
            Log.d("settings", "new value for downloadType: " + sharedPreferences.getString(s, null));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            LanguageList.setStandardOptionLabel(getString(R.string.default_language));

        }

        @Override
        public void onPause() {
            Language.setFromPreference(getActivity(), "myPreference", true);

            super.onPause();
        }
    }

}

