package com.cristhoper.mypersonalapp.preferences;

import android.app.Application;
import android.content.res.Configuration;

import im.delight.android.languages.Language;

/**
 * Created by Cris on 23/10/2017.
 */

public class LanguagePreference extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Language.setFromPreference(this, "myPreferenceKey");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Language.setFromPreference(this, "myPreferenceKey");
    }
}
