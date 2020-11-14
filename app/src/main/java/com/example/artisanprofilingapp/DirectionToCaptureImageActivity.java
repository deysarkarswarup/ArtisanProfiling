package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.IOException;

public class DirectionToCaptureImageActivity extends AppCompatActivity {
    private Button nextBtn;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_to_capture_image);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nextBtn = findViewById(R.id.nextBtn);
        mediaPlayer = MediaPlayer.create(this, R.raw.slide6);

        mediaPlayer.start();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(new Intent(DirectionToCaptureImageActivity.this,ImageCaptureSelection.class));
            }
        });

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