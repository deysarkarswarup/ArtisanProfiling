package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class UpdateSelectionAcivity extends AppCompatActivity {
    Button phoneno, name, age, address, profilePic, artformPicture,experience, silpo, porichitiVideo,artformVideo,newData;
    private SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_selection_acivity);

        phoneno = findViewById(R.id.phoneno);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        profilePic = findViewById(R.id.profilePic);
        artformPicture = findViewById(R.id.artformPicture);
        experience = findViewById(R.id.experience);
        silpo = findViewById(R.id.silpo);
        newData = findViewById(R.id.newData);
//        porichitiVideo = findViewById(R.id.porichitiVideo);
//        artformVideo = findViewById(R.id.artformVideo);

        myPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        mediaPlayer = MediaPlayer.create(this, R.raw.whichdatachange);

        mediaPlayer.start();
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            phoneno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    myPref.edit().putString("selected", "saree").apply();
//                    myPref.edit().putString("track", "9").apply();
                    Log.d("eirki", "onClick: ");
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, EditPhoneActivity.class));
                }
            });

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    myPref.edit().putString("selected", "saree").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, EditNameActivity.class));
                }
            });

            age.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    myPref.edit().putString("selected", "saree").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, EditAgeActivity.class));
                }
            });

            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, EditAddressActivity.class));
                }
            });

            experience.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, EditExperienceActivity.class));
                }
            });

            silpo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    myPref.edit().putString("selected", "saree").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, EditArtformActivity.class));
                }
            });
            newData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, UserTypeActivity.class));
                }
            });
//            porichitiVideo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    myPref.edit().putString("selected", "saree").apply();
////                    myPref.edit().putString("track", "9").apply();
//                    mediaPlayer.stop();
//                    startActivity(new Intent(UpdateSelectionAcivity.this, Insert_image_instructionActivity.class));
//                }
//            });
//
//            artformVideo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    myPref.edit().putString("selected", "saree").apply();
////                    myPref.edit().putString("track", "9").apply();
//                    mediaPlayer.stop();
//                    startActivity(new Intent(UpdateSelectionAcivity.this, Insert_image_instructionActivity.class));
//                }
//            });

            profilePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    myPref.edit().putString("selected", "saree").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(UpdateSelectionAcivity.this, EditProfilePicActivity.class));
                }
            });

            artformPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    myPref.edit().putString("selected", "saree").apply();
//                    myPref.edit().putString("track", "9").apply();
                    mediaPlayer.stop();
                    myPref.edit().putString("update","1").apply();
                    startActivity(new Intent(UpdateSelectionAcivity.this, ImageCaptureSelection.class));
                }
            });





        } else {
            Intent intent = new Intent(UpdateSelectionAcivity.this, InternetCheckActivity.class);
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