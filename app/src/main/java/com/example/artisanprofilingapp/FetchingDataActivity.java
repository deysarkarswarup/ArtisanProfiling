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
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class FetchingDataActivity extends AppCompatActivity {
    Button finish,newentry;
    SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    private String idToGet="";
    private String toUpdateFlag="";

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetching_data);

        finish = (Button) findViewById(R.id.finish);
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        myPref.edit().putString("track","100").apply();
        idToGet = myPref.getString("id","No data found");
        toUpdateFlag = myPref.getString("toUpdateFlag","No data found");
        webView = findViewById(R.id.webView);
        WebSettings wbs = webView.getSettings();
        wbs.setJavaScriptEnabled(true);
        wbs.setMediaPlaybackRequiresUserGesture(false);
        webView.loadUrl("http://artisanapp.xyz/fetchingData.php?id="+idToGet);

        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
Intent intent = new Intent(FetchingDataActivity.this, ThankYouActivity.class);
                        startActivity(intent);

                }
            });

        }
        else{
            Intent intent = new Intent(FetchingDataActivity.this, InternetCheckActivity.class);
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
    public void onUserLeaveHint(){
        super.onUserLeaveHint();
        myPref.edit().putString("track","100").apply();
    }
}