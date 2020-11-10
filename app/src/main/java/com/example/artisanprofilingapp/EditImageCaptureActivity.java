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

public class EditImageCaptureActivity extends AppCompatActivity {
    Button saree, kurtya, tshirt, showpiece, bag, goina, other;
    private SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image_capture);

        saree = findViewById(R.id.saree);
        kurtya = findViewById(R.id.kurta);
        tshirt = findViewById(R.id.tshirt);
        showpiece = findViewById(R.id.showPiece);
        bag = findViewById(R.id.bag);
        goina = findViewById(R.id.goina);
        other = findViewById(R.id.others);
        myPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        mediaPlayer = MediaPlayer.create(this, R.raw.captureselectioninst);

        mediaPlayer.start();
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            saree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "saree").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), EditInsert_image_instructionActivity.class));
                }
            });
            kurtya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "kurta").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), EditInsert_image_instructionActivity.class));
                }
            });
            tshirt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "tshirt").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), EditInsert_image_instructionActivity.class));
                }
            });
            showpiece.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "showpiece").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), EditInsert_image_instructionActivity.class));
                }
            });
            bag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "bag").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), EditInsert_image_instructionActivity.class));
                }
            });
            goina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "goina").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), EditInsert_image_instructionActivity.class));
                }
            });

            other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    //myPref.edit().putString("track", "9").apply();//kora hoini.. pore korbo
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), EditCaptureImageActivity.class));
                }
            });

        }
        else{
            Intent intent = new Intent(EditImageCaptureActivity.this, InternetCheckActivity.class);
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