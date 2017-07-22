package com.example.micah.profanitybot.WeatherRetrieval.WeatherJsonModel;

public class WeatherStatsInfo {

    double temp;
    int pressure;
    int humidity;
    double temp_min;
    double temp_max;

    @Override
    public String toString() {
       return "temp: " + temp
               + " pressure: " + pressure
                 + " humidity: " + humidity
                    + " temp_min: " + temp_min
                        + " temp_max: " + temp_max;
    }

    public String getSpeechRepresentation(){

        return "The current temperature is " + temp;
    }
}
