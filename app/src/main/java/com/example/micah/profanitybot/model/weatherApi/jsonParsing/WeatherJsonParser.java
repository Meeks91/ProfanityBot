package com.example.micah.profanitybot.model.weatherApi.jsonParsing;

import com.example.micah.profanitybot.model.weatherApi.jsonParsing.WeatherJsonModel.WeatherInfo;
import com.google.gson.Gson;

/**
 * Created by Micah on 01/09/2017.
 */

public class WeatherJsonParser {

   private final Gson gson = new Gson();

    public WeatherInfo exctractWeatherInfoFrom(String jsonWeatherInfoStringt){

       return gson.fromJson(jsonWeatherInfoStringt, WeatherInfo.class);
    }
}
