package com.example.micah.profanitybot.model.API_AI;

import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import com.example.micah.profanitybot.model.weatherApi.WeatherApiHelper;
import com.example.micah.profanitybot.view.MainActivityViewDelegate;

import ai.api.model.AIResponse;

/**
 * Created by Micah on 28/05/2017.
 */

public class SpeechMaker {

    private final String TAG = SpeechMaker.class.getSimpleName();
    private final String RECEIVING_PROFOANITY_TOKEN = "Receiving a profanity token";
    private SpeechSynthesiser speechSynthesiser;
    private WeatherApiHelper weatherRetriever;
    private MainActivityViewDelegate mainActivityViewDelegate;

    public SpeechMaker(SpeechSynthesiser speechSynthesiser, WeatherApiHelper weatherRetriever, MainActivityViewDelegate mainActivityViewDelegate){

        this.mainActivityViewDelegate = mainActivityViewDelegate;

        this.speechSynthesiser = speechSynthesiser;

        this.weatherRetriever = weatherRetriever;
    }

    /**
     * routes the speech contained in the result to the speech synthesiser.
     * It passes a completion handler to fetch weather data which is then spoken
     * if the @result's intent's name == RECEIVING_PROFOANITY_TOKEN
     *
     * @param result - an AIResponse which contains what speech to synthesis
     *                 and an intent name which notifies us if we should
     *                 fetch and speak weather data
     */
    public void routeAiResponseIntoSpeech(AIResponse result) {

        //check if we should fetch the weather
        boolean shouldFetchWeather = result.getResult().getMetadata().getIntentName().equals(RECEIVING_PROFOANITY_TOKEN);

        //speak the current speech and provide a callback value
        speechSynthesiser.convertTextToSpeech(result.getResult().getFulfillment().getSpeech(), createUtterListener(shouldFetchWeather));
    }

    //provides a listener for the speechevents when the speech is handling weather retrieval (i.e. "I am fetching weather").
    // When the speech is done it fetches the weather data and speak it out
    private UtteranceProgressListener createUtterListener(final boolean getAndSpeakWeatherOnDone){

        return new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
              }

            @Override
            public void onDone(String utteranceId) {

                if (getAndSpeakWeatherOnDone == true) {

                    //get the weather data and then speak it once it has been retrieved
                    getAndSpeakWeatherData();
                }

                mainActivityViewDelegate.onSpeechEnded();
            }

            @Override
            public void onError(String utteranceId) {
            }
        };
    }

    /**
     * gets the weather data and speaks it once it has been retrieved
     */
    private void getAndSpeakWeatherData(){

        weatherRetriever.getWeather(weatherInfo -> {

                //speechSynthesiser.convertTextToSpeech(weatherInfo.);
            });
    }
}
