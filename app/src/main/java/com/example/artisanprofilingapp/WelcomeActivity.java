package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button fetch, update;
    SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    private String idToGet = "";
    private String toUpdateFlag = "";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        fetch = (Button) findViewById(R.id.fetch);
        update = (Button) findViewById(R.id.update);

        myPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        myPref.edit().putString("track","100").apply();
        idToGet = myPref.getString("id", "No data found");
        toUpdateFlag = myPref.getString("toUpdateFlag", "No data found");
        webView = findViewById(R.id.webView);
        WebSettings wbs = webView.getSettings();
        wbs.setJavaScriptEnabled(true);
        wbs.setMediaPlaybackRequiresUserGesture(false);
        webView.loadUrl("http://artisanapp.xyz/welcomeMsg.php?id=" + idToGet);

        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            fetch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WelcomeActivity.this, FetchingDataActivity.class);
                    startActivity(intent);

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WelcomeActivity.this, UpdateChooseActivity.class);
                    startActivity(intent);

                }
            });

        } else {
            Intent intent = new Intent(WelcomeActivity.this, InternetCheckActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myPref.edit().putString("track","100").apply();
    }

    @Override
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        myPref.edit().putString("track","100").apply();
    }
}