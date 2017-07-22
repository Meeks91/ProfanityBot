package com.example.micah.profanitybot.Dagger;

import javax.inject.Singleton;

import ai.api.http.HttpClient;
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
}
