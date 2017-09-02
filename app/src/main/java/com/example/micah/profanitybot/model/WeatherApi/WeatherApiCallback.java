package com.example.micah.profanitybot.model.weatherApi;

import com.example.micah.profanitybot.model.weatherApi.jsonParsing.WeatherJsonModel.WeatherInfo;

public interface WeatherApiCallback {

    public void onWeatherRetrieved(WeatherInfo weatherInfo);
}
