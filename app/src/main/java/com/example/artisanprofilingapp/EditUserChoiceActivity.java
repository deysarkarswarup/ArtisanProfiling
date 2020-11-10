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

import java.io.IOException;

public class EditUserChoiceActivity extends AppCompatActivity {
    Button yes,no;
    SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_choice);

        yes = (Button) findViewById(R.id.yesBtn);
        no = (Button) findViewById(R.id.noBtn);
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        mediaPlayer = MediaPlayer.create(this, R.raw.userchoiceinst);

        mediaPlayer.start();
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.stop();
                    Intent i = new Intent(EditUserChoiceActivity.this, EditCaptureImageActivity.class);
                    startActivity(i);
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    myPref.edit().putString("track", "12").apply();
                    mediaPlayer.stop();
                    Intent i = new Intent(EditUserChoiceActivity.this, UpdateSelectionAcivity.class);
                    startActivity(i);
                }
            });
        }
        else{
            Intent intent = new Intent(EditUserChoiceActivity.this, InternetCheckActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        super.onBackPressed();
    }
    @Override
    public void onUserLeaveHint(){
        mediaPlayer.stop();
        super.onUserLeaveHint();
    }
}