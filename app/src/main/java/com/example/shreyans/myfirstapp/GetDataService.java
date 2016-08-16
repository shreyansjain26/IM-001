package com.example.shreyans.myfirstapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GetDataService extends IntentService {

    public GetDataService(){
        super(null);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        String query = workIntent.getDataString();
        //Log.d("onHandleIntent",dataString);

        try {
            URL url = new URL("http://www.omdbapi.com/?t="+query);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String jsonString;
            jsonString = br.readLine();
            JSONObject jObject = new JSONObject(jsonString);
            String imdbRating = (String) jObject.get("imdbRating");
            Intent rating = new Intent();
            rating.setAction("yolo");
            rating.putExtra("imdbRating",imdbRating);
            Log.d("imdbRating",imdbRating);
            this.sendBroadcast(rating);


        } catch (Exception e) {
            String imdbRating = "Error Fetching Data";
            Log.e("urlError",imdbRating);
        }


    }


}
