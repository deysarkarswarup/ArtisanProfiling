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
import android.widget.Button;

public class ThankYouActivity extends AppCompatActivity {
    Button finish,newentry;
    SharedPreferences myPref;
    //private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        finish = (Button) findViewById(R.id.finish);
//        newentry = (Button) findViewById(R.id.newEntry);
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        //mediaPlayer = MediaPlayer.create(this, R.raw.thankyouinst);
        //mediaPlayer.start();
        myPref.edit().putString("track","19").apply();
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myPref.edit().putString("track","100").apply();
                    //mediaPlayer.stop();
                    finishAffinity();
                    System.exit(0);
                }
            });

        }
        else{
            Intent intent = new Intent(ThankYouActivity.this, InternetCheckActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        //mediaPlayer.stop();
        super.onBackPressed();
        myPref.edit().putString("track","0").apply();
    }
    @Override
    public void onUserLeaveHint(){
        //mediaPlayer.stop();
        super.onUserLeaveHint();
        myPref.edit().putString("track","0").apply();
    }
}