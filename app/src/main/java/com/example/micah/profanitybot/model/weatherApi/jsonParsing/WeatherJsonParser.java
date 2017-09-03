package com.example.micah.profanitybot.model.weatherApi.jsonParsing;

import com.example.micah.profanitybot.model.weatherApi.jsonParsing.WeatherJsonModel.WeatherInfo;
import com.google.gson.Gson;

/**
 * Created by Micah on 01/09/2017.
 */

public class WeatherJsonParser {

   private final Gson gson = new Gson();

    /**
     * returns a WeatherInfo  object created using the specified
     * jsonWeatherInfoStringby means of Gson parsing
     *
     * @param jsonWeatherInfoString
     * @return
     */
    public WeatherInfo exctractWeatherInfoFrom(String jsonWeatherInfoString){

       return gson.fromJson(jsonWeatherInfoString, WeatherInfo.class);
    }
}
