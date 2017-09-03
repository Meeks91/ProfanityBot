package com.example.micah.profanitybot;

import com.example.micah.profanitybot.gifRetrieval.GifRetriever;
import com.example.micah.profanitybot.model.API_AI.ApiAiListener;
import com.example.micah.profanitybot.view.MainActivityViewDelegate;

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

        this.aiService = aiService;

        this.apiAiListener = apiAiListener;

        this.mainActivityViewDelegate = mainActivityViewDelegate;

        this.gifRetriever = gifRetriever;

        setAiServiceListener();
    }

    //MARK: -----------  INITIALISATION METHODS

    /**
     * assigns the listener to the aiService
     */
    private void setAiServiceListener() {

        //assign the listener to the aiService
        aiService.setListener(apiAiListener);
    }

    //MARK: -----------  INITIALISATION METHODS

    //MARK: ------------ ROUTING RECEIEVED INPUTS

    /**
     * retrieves a gif from the api and then send it to the view
     */
    public void getRandomGif() {

        gifRetriever.getRandomGif(gifUrl -> {

                if (gifUrl != null) {

                    mainActivityViewDelegate.onGifRetrieved(gifUrl);
                }
        });
    }

    /**
     * starts the aiService which listens for speech
     */
    public void startTheAiServiceListeningForSpeech() {

        //make the aiService start listening for speech
        aiService.startListening();
    }

    //MARK: ------------ ROUTING RECEIEVED INPUTS
}