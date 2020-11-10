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

public class ImageCaptureSelection extends AppCompatActivity {
    Button saree, goina, bag, tshirt, wrapperskirt, palazzo, cushioncover, leatherbag, blousepiece, kurta, homedecor,
            utility, painting, stoles, handkerchief,other;
    private SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture_selection);

        saree = findViewById(R.id.saree);
        goina = findViewById(R.id.goina);
        bag = findViewById(R.id.bag);
        tshirt = findViewById(R.id.tshirt);
        wrapperskirt = findViewById(R.id.wrapperskirt);
        palazzo = findViewById(R.id.palazzo);
        cushioncover = findViewById(R.id.cushioncover);
        leatherbag = findViewById(R.id.leatherbag);
        blousepiece = findViewById(R.id.blousepiece);
        kurta = findViewById(R.id.kurta);
        homedecor = findViewById(R.id.homedecor);
        utility = findViewById(R.id.utility);
        painting = findViewById(R.id.painting);
        stoles = findViewById(R.id.stoles);
        handkerchief = findViewById(R.id.handkerchief);
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
                    myPref.edit().putString("ProductName", "শাড়ি").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });
            goina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "goina").apply();
                    myPref.edit().putString("ProductName", "গয়না").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });
            bag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "bag").apply();
                    myPref.edit().putString("ProductName", "ব্যাগ").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });
            tshirt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "tshirt").apply();
                    myPref.edit().putString("ProductName", "টি-শার্ট").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            wrapperskirt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "wrapperskirt").apply();
                    myPref.edit().putString("ProductName", "স্কার্ট").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            palazzo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "palazzo").apply();
                    myPref.edit().putString("ProductName", "পালাজো").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            cushioncover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "cushioncover").apply();
                    myPref.edit().putString("ProductName", "কুশন কভার").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            leatherbag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "leatherbag").apply();
                    myPref.edit().putString("ProductName", "চামড়ার ব্যাগ").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            blousepiece.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "blousepiece").apply();
                    myPref.edit().putString("ProductName", "ব্লাউজ পিস").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });

            kurta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "kurta").apply();
                    myPref.edit().putString("ProductName", "কুর্তা কুর্তি").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });

            homedecor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "homedecor").apply();
                    myPref.edit().putString("ProductName", "শো-পিস").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });

            utility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "utility").apply();
                    myPref.edit().putString("ProductName", "অফিস আইটেম").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            painting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "painting").apply();
                    myPref.edit().putString("ProductName", "পেইন্টিং").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            stoles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "stoles").apply();
                    myPref.edit().putString("ProductName", "স্টোল").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });


            handkerchief.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "handkerchief").apply();
                    myPref.edit().putString("ProductName", "রুমাল").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), Insert_image_instructionActivity.class));
                }
            });

            other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("ProductName", "অন্যান্য").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), CaptureImageActivity.class));
                }
            });

        }
        else{
            Intent intent = new Intent(ImageCaptureSelection.this, InternetCheckActivity.class);
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