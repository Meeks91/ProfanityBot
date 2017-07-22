package com.example.micah.profanitybot.API_AI;

import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import com.example.micah.profanitybot.MainActivity;
import com.example.micah.profanitybot.Presenter.MainActivityViewDelegate;
import com.example.micah.profanitybot.WeatherRetrieval.WeatherInfoCallback;
import com.example.micah.profanitybot.WeatherRetrieval.WeatherJsonModel.WeatherResponse;
import com.example.micah.profanitybot.WeatherRetrieval.WeatherRetriever;

import ai.api.model.AIResponse;

/**
 * Created by Micah on 28/05/2017.
 */

public class SpeechMaker {

    private final String TAG = ApiAiListener.class.getSimpleName();
    private SpeechSynthesiser speechSynthesiser;
    private WeatherRetriever weatherRetriever;
    private MainActivityViewDelegate mainActivityViewDelegate;

    public SpeechMaker(SpeechSynthesiser speechSynthesiser, WeatherRetriever weatherRetriever, MainActivityViewDelegate mainActivityViewDelegate){

        //set the global onSpeechFinishedDelegate
        this.mainActivityViewDelegate = mainActivityViewDelegate;

        //set the global speechSynthesiser
        this.speechSynthesiser = speechSynthesiser;

        //set the global weatherRetriever
        this.weatherRetriever = weatherRetriever;
    }

    //routes the speech contained in the result to the speech synthesiser. It passes a completion handler to fetch weather data
    //which is then spoken  - if the type of intent in the result is of the appropriate type
    public void routeAiResponseIntoSpeech(AIResponse result) {

        //check if we should fetch the weather
        boolean shouldFetchWeather = result.getResult().getMetadata().getIntentName().equals("Receiving a profanity token");

        Log.d(TAG, "routeAiResponseIntoSpeech: shouldFetchWeather is: " + shouldFetchWeather);

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

                Log.d(TAG, "UtteranceProgressListener: onDone called");


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

    //gets the weather data and speaks it once it has been retrieved
    private void getAndSpeakWeatherData(){

        //get the weather info
        weatherRetriever.getWeather(new WeatherInfoCallback() {
            @Override
            public void onWeatherRetrieved(WeatherResponse weatherResponse) {

                //speak the retrieved weather data
                speechSynthesiser.convertTextToSpeech(weatherResponse.main.getSpeechRepresentation(), null);
            }});
    }
}
