package com.example.micah.profanitybot.Dagger;

import android.content.Context;

import com.example.micah.profanitybot.API_AI.ApiAiListener;
import com.example.micah.profanitybot.API_AI.OnSpeechFinishedDelegate;
import com.example.micah.profanitybot.API_AI.SpeechMaker;
import com.example.micah.profanitybot.API_AI.SpeechSynthesiser;
import com.example.micah.profanitybot.MainActivity;
import com.example.micah.profanitybot.Presenter.MainActivityPresenter;
import com.example.micah.profanitybot.Presenter.MainActivityViewDelegate;
import com.example.micah.profanitybot.WeatherRetrieval.WeatherRetriever;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Micah on 28/05/2017.
 */

@Module
public class SpeechModule {

    @Provides
    public SpeechSynthesiser provideSpeechSynthesiser(Context context){

        return new SpeechSynthesiser(context);
    }

    @Provides
    public SpeechMaker provideSpeechMaker(SpeechSynthesiser speechSynthesiser, WeatherRetriever weatherRetriever, Context mainActivityViewDelegate){

        return new SpeechMaker(speechSynthesiser, weatherRetriever, (MainActivityViewDelegate) mainActivityViewDelegate);
    }

}
