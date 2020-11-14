package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class NewDirectionToCaptureImageActivity extends AppCompatActivity {
    private Button nextBtn;
    private MediaPlayer mediaPlayer;
    SharedPreferences myPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_direction_to_capture_image);

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        myPref.edit().putString("track", "5").apply();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nextBtn = findViewById(R.id.nextBtn);
        mediaPlayer = MediaPlayer.create(this, R.raw.slide5);
        mediaPlayer.start();
        //At the end
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(new Intent(NewDirectionToCaptureImageActivity.this,DirectionToCaptureImageActivity.class));
            }
        });

    }
    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        myPref.edit().putString("track", "5").apply();
        super.onBackPressed();
    }
    @Override
    public void onUserLeaveHint(){
        mediaPlayer.stop();
        myPref.edit().putString("track", "5").apply();
        super.onUserLeaveHint();
    }
}