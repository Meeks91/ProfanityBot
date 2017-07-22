package com.example.micah.profanitybot.API_AI;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import com.example.micah.profanitybot.*;
import com.example.micah.profanitybot.Application;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Micah on 28/05/2017.
 */

public class SpeechSynthesiser implements  Application.ActivityLifecycleCallbacks {

    private final String TAG = SpeechSynthesiser.class.getSimpleName();
    private TextToSpeech textToSpeechEngine;
    private Context context;

    public SpeechSynthesiser(Context context) {

        //set the global context
        this.context = context;

        //register for the lifeCycle backs
        registerForLifeCycleCallbacks();
    }

    //MARK: -------- INITIALISATION METHODS

    //registers the class to receive lifecycle callbacks. Need to stop the textToSpeech engine in onPause
    private void registerForLifeCycleCallbacks() {

        //register the class for callbacks via the Application class
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this);
    }

    //initiates the textToSpeechEngine and instantiates the TextToSpeech.OnInitListener
    private void initTextToSpeechEngine() {

        Log.d(TAG, "initTextToSpeechEngine() called");

        //init the TextToSpeech and create the TextToSpeech.OnInitListener
        //Use application content to avoid textToSpeechEngine leaking out of the activity
        textToSpeechEngine = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {

                //check if the initialisation was a success
                if (status == TextToSpeech.SUCCESS) {

                    //set the textToSpeechEngine and get the result
                    int result = textToSpeechEngine.setLanguage(Locale.UK);

                    //check if we set the language successfully
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                        Log.e(TAG, "error: this Language is not supported");
                    }
                }

                //here we didn't successfully initialise the textToSpeechEngine
                else {

                    Log.e("error", "Initilization Failed!");
                }
            }
        });
    }

    //MARK: -------- INITIALISATION METHODS

    //MARK: -------- textToSpeechEngine OPERATIONAL METHODS

    //method which makes the textToSpeechEngine speak the specified text and sets a progress listener
    protected void convertTextToSpeech(String textToSpeak, UtteranceProgressListener utteranceProgressListener) {

        //set the utteranceProgressListener
        textToSpeechEngine.setOnUtteranceProgressListener(utteranceProgressListener);

        //speak the specified text
        textToSpeechEngine.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null,  "" + System.currentTimeMillis());
    }

    //stops the textToSpeechEngine to save resources
    private void stopTheTextToSpeechEngine() {

        Log.d(TAG, "stopTheTextToSpeechEngine() called");

        //stop the textToSpeechEngine - stops the speaking
        textToSpeechEngine.stop();

        //shutdown the textToSpeechEngine - gets back the resources
        textToSpeechEngine.shutdown();

        textToSpeechEngine = null;
    }

    //MARK: -------- textToSpeechEngine OPERATIONAL METHODS

    //MARK: -------- LIFECYCLE CALLBACKS

    //stops the class from receiving lifeycle callbacks
    private void unregisterFomLifeLifecycleCallbacks(){

        //unregister the lifecycle callbacks
        ((Application) context.getApplicationContext()).unregisterActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

        //init the textToSpeechEngine
        initTextToSpeechEngine();
    }

    @Override
    public void onActivityPaused(Activity activity) {

        Log.d(TAG, "onActivityPaused called()");

        //stop to the textToSpeechEngine
        stopTheTextToSpeechEngine();

        //unregisters the class from lifecycle callbacks. Need to unregister to stop double registration
        unregisterFomLifeLifecycleCallbacks();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}