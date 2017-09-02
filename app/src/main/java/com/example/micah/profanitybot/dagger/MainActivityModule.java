package com.example.micah.profanitybot.dagger;

import android.content.Context;


import com.example.micah.profanitybot.gifRetrieval.GifRetriever;
import com.example.micah.profanitybot.MainActivityPresenter;
import com.example.micah.profanitybot.model.API_AI.ApiAiListener;
import com.example.micah.profanitybot.model.API_AI.SpeechMaker;
import com.example.micah.profanitybot.model.weatherApi.WeatherApiHelper;
import com.example.micah.profanitybot.model.weatherApi.jsonParsing.WeatherJsonParser;
import com.example.micah.profanitybot.view.MainActivityViewDelegate;

import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Micah on 27/05/2017.
 */

@Module
public class MainActivityModule {

    private final Context context;

    public MainActivityModule(Context context) {

        //set the global context
        this.context = context;
    }

    @Provides
    public Context provideContext(){

        return context;
    }

    @Provides
    public MainActivityPresenter providesMainActivityPresenter(Context context, GifRetriever gifRetriever, AIService aiService, ApiAiListener apiAiListener ){

        return new MainActivityPresenter((MainActivityViewDelegate) context, gifRetriever, aiService, apiAiListener);
    }
}
