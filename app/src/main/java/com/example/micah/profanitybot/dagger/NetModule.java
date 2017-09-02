package com.example.micah.profanitybot.dagger;

import com.example.micah.profanitybot.gifRetrieval.GifRetriever;
import com.example.micah.profanitybot.model.weatherApi.WeatherApiHelper;
import com.example.micah.profanitybot.model.weatherApi.jsonParsing.WeatherJsonParser;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Micah on 28/05/2017.
 */

@Module
public class NetModule {

     @Provides
    public OkHttpClient providesSomething(){

        return new OkHttpClient.Builder().build();
    }

    @Provides
    public GifRetriever providesGifRetriever(OkHttpClient okHttpClient){

        return new GifRetriever(okHttpClient);
    }

    @Provides
    public WeatherApiHelper provideWeatherRetriever(OkHttpClient okHttpClient, WeatherJsonParser weatherJsonParser){

        return new WeatherApiHelper(okHttpClient, weatherJsonParser);
    }

    @Provides WeatherJsonParser provideWeatherJsonParser(){

        return new WeatherJsonParser();
    }
}
