package com.example.micah.profanitybot.Dagger;

import android.content.Context;

import com.example.micah.profanitybot.API_AI.ApiAiListener;
import com.example.micah.profanitybot.API_AI.SpeechMaker;
import com.example.micah.profanitybot.GifRetrieval.GifRetriever;
import com.example.micah.profanitybot.Presenter.MainActivityPresenter;
import com.example.micah.profanitybot.Presenter.MainActivityViewDelegate;
import com.example.micah.profanitybot.WeatherRetrieval.WeatherRetriever;

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

    @Provides
    public GifRetriever providesGifRetriever(OkHttpClient okHttpClient){

        return new GifRetriever(okHttpClient);
    }

    @Provides
    public AIConfiguration provideAIConfiguration(){

        return new AIConfiguration("39bb612abbff4efd9ba7efea835f1249",
                       AIConfiguration.SupportedLanguages.English,
                           AIConfiguration.RecognitionEngine.System);
    }

    @Provides
    public AIService provideAIService(Context context, AIConfiguration aiConfiguration){

        return AIService.getService(context, aiConfiguration);
    }

    @Provides
    public ApiAiListener provideApiAiOperator(SpeechMaker speechMaker, Context context){

        return new ApiAiListener(speechMaker, (MainActivityViewDelegate) context);
    }

    @Provides
    public WeatherRetriever provideWeatherRetriever(OkHttpClient okHttpClient){

        return new WeatherRetriever(okHttpClient);
    }
}
