package com.example.micah.profanitybot.WeatherRetrieval;

import com.example.micah.profanitybot.WeatherRetrieval.WeatherJsonModel.WeatherResponse;

public interface WeatherInfoCallback {

    public void onWeatherRetrieved(WeatherResponse weatherResponse);

}
