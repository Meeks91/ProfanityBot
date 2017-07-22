package com.example.micah.profanitybot.Presenter;

/**
 * Created by Micah on 18/06/2017.
 */

public interface MainActivityViewDelegate {

    void onGifRetrieved(String randomGif);

    void onSpeechEnded();
}
