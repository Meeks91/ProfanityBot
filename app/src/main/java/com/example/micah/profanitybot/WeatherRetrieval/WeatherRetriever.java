package com.example.micah.profanitybot.WeatherRetrieval;

import android.os.AsyncTask;
import android.util.Log;

import com.example.micah.profanitybot.WeatherRetrieval.WeatherJsonModel.WeatherResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Micah on 28/05/2017.
 */

public class WeatherRetriever {

    private final String TAG = WeatherRetriever.class.getSimpleName();
    private final OkHttpClient okHttpClient;

    public WeatherRetriever(OkHttpClient okHttpClient){

        //set the global okHttpClient
        this.okHttpClient = okHttpClient;
    }

    public WeatherResponse getWeather(final WeatherInfoCallback weatherInfoCallback){

            //execute an asyncTask for the networking call
            new AsyncTask<Object, Void, Object>() {
                @Override
                protected Object doInBackground(Object[] params) {

                    try {

                        //create the request
                        Request request = new Request.Builder().url("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=4c4af81b73d7c5aab0d36279be747391").build();

                        //get the resoonse
                        Response response = okHttpClient.newCall(request).execute();

                        Gson gson = new Gson();

                        //Log.d(TAG, "getWeather: response.body.toString() is: " + response.body().string());

                       WeatherResponse weatherResponse = gson.fromJson(response.body().string(), WeatherResponse.class);

                        Log.d(TAG, "getWeather: weatherResponse.main is: " + weatherResponse.main.toString());

                        weatherInfoCallback.onWeatherRetrieved(weatherResponse);
                    }

                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            }.execute();

        return null;
    }
}

