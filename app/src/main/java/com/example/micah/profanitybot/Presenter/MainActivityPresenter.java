package com.example.micah.profanitybot.Presenter;

import com.example.micah.profanitybot.API_AI.ApiAiListener;
import com.example.micah.profanitybot.API_AI.OnSpeechFinishedDelegate;
import com.example.micah.profanitybot.GifRetrieval.GifRetrievalCallback;
import com.example.micah.profanitybot.GifRetrieval.GifRetriever;

import javax.inject.Inject;

import ai.api.android.AIService;

/**
 * Created by Micah on 18/06/2017.
 */

public class MainActivityPresenter  {

    private AIService aiService;
    private ApiAiListener apiAiListener;
    private MainActivityViewDelegate mainActivityViewDelegate;
    private GifRetriever gifRetriever;

    public MainActivityPresenter(MainActivityViewDelegate mainActivityViewDelegate, GifRetriever gifRetriever, AIService aiService, ApiAiListener apiAiListener) {

        //set the global aiService
        this.aiService = aiService;

        //set the global apiListener
        this.apiAiListener = apiAiListener;

        //set the global mainActivityViewDelegate
        this.mainActivityViewDelegate = mainActivityViewDelegate;

        //set the global gifRetiever
        this.gifRetriever = gifRetriever;

        //set the listener for the aiServer
        setAiServiceListener();
    }

    //MARK: -----------  INITIALISATION METHODS

    //assigns the listener to the aiService
    private void setAiServiceListener() {

        //assign the listener to the aiService
        aiService.setListener(apiAiListener);
    }

    //MARK: -----------  INITIALISATION METHODS

    //MARK: ------------ ROUTING RECEIEVED INPUTS

    public void getRandomGif() {

        gifRetriever.getRandomGif(new GifRetrievalCallback() {

            @Override
            public void onGifRetrieved(String gifUrl) {

                if (gifUrl != null) {

                    mainActivityViewDelegate.onGifRetrieved(gifUrl);
                }
            }
        });
    }

    public void startTheAiServiceListeningForSpeech() {

        //make the aiService start listening for speech
        aiService.startListening();
    }

    //MARK: ------------ ROUTING RECEIEVED INPUTS

}