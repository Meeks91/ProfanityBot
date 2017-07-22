package com.example.micah.profanitybot.Dagger;

import com.example.micah.profanitybot.MainActivity;

import dagger.Component;

/**
 * Created by Micah on 27/05/2017.
 */


@Component (modules = {MainActivityModule.class, NetModule.class, SpeechModule.class})

public interface AppComponent {

    public void inject(MainActivity mainActivity);
}
