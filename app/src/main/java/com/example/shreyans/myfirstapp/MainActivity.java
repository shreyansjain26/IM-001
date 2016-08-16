package com.example.shreyans.myfirstapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReciever reciever = new broadcastReciever();
        registerReceiver(reciever, new IntentFilter("yolo"));
    }

    public void clickButton(View view) {
        //Log.d("clickButton","1");
        String query = ((EditText)findViewById(R.id.word)).getText().toString();
        //String uri = "http://www.geek.com/wp-content/uploads/2016/02/batmans.jpg";
        Intent dataService = new Intent(this,GetDataService.class);
        dataService.setData(Uri.parse(query));
        //Log.d("Here we go","2");
        this.startService(dataService);

    }
    public class broadcastReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String rating = intent.getStringExtra("imdbRating");
            String imdb = "IMDB Rating: ";
            String imdbRating = imdb+rating;
            ((TextView) findViewById(R.id.response)).setText(imdbRating);

        }
    }
}
