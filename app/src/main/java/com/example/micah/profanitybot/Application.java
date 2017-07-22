package com.example.micah.profanitybot;

import android.content.Context;

import com.example.micah.profanitybot.Dagger.MainActivityModule;
import com.example.micah.profanitybot.Dagger.DaggerAppComponent;

/**
 * Created by Micah on 27/05/2017.
 */

public class Application extends android.app.Application {

    private DaggerAppComponent daggerAppComponent;

    public static DaggerAppComponent getAppComponent(Context context) {

        //check if there already if we alrady made the daggerAppComponent
        if (((Application) context.getApplicationContext()).daggerAppComponent != null)

            return ((Application) context.getApplicationContext()).daggerAppComponent;

        //Here we hadn't already made the daggerAppComponent so we make one
        ((Application) context.getApplicationContext()).daggerAppComponent = (DaggerAppComponent) DaggerAppComponent.builder().mainActivityModule(new MainActivityModule(context)).build();

        return ((Application) context.getApplicationContext()).daggerAppComponent;
    }
}

