package com.example.micah.profanitybot.GifRetrieval;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Micah on 18/06/2017.
 */

public class GifRetriever {

    private OkHttpClient okHttpClient;

    public GifRetriever(OkHttpClient okHttpClient){

        this.okHttpClient = okHttpClient;
    }

    public void getRandomGif(final GifRetrievalCallback gifRetrievalCallback){

        final Request request = new Request.Builder().url("http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {

                 e.printStackTrace();

                 gifRetrievalCallback.onGifRetrieved(null);
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {

                 String gifUrl = null;

                 try {

                   JSONObject gifResponseBody = new JSONObject(response.body().string());

                   gifUrl =  ((JSONObject) gifResponseBody.get("data")).getString("image_url");

                 } catch (JSONException e) {

                     e.printStackTrace();
                 }

                 gifRetrievalCallback.onGifRetrieved(gifUrl);
             }
         });
    }
}

