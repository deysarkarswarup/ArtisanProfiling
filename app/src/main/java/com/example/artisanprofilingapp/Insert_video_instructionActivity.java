package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Insert_video_instructionActivity extends AppCompatActivity {
Button submitbtn;
SharedPreferences myPref;
    private boolean mResumed = false;
    private boolean mFocused = false;
    private boolean mControlResumed = false;
    private VideoView videoView = null;
    private int stopPosition = 0;
//    private final MediaController mediaController = null;

private MediaPlayer mediaPlayer, mediaPlayer2;

    VideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_insert_video_instruction);
        mediaPlayer = MediaPlayer.create(this, R.raw.profilevideoinst);

        mediaPlayer.start();
//
//        mediaPlayer2 = MediaPlayer.create(this, R.raw.bikasdas);
//        mediaPlayer2.start();
        submitbtn = (Button) findViewById(R.id.submitBtnn);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
//        videoView =(VideoView)findViewById(R.id.videoview);
        final MediaController mediaController= new MediaController(this);

//        final Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bikasdas);


//        videoView.requestFocus();

//        onPause();

        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    mediaController.setAnchorView(videoView);
//                    videoView.setMediaController(mediaController);
//                    videoView.setVideoURI(uri);
//                    videoView.requestFocus();
//                    videoView.start();
//                }
//            });
            submitbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("track", "13").apply();
                    mediaPlayer.stop();
                    //videoView.stopPlayback();
                    Intent i = new Intent(Insert_video_instructionActivity.this, CaptureVideoActivity.class);
                    startActivity(i);
                }
            });
        }
        else{
            Intent intent = new Intent(Insert_video_instructionActivity.this, InternetCheckActivity.class);
            startActivity(intent);
            finish();
        }


    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mResumed = false;
//        if (mControlResumed) {
//            if (null != videoView)
//                videoView.pause();
//            stopPosition = videoView.getCurrentPosition();
//            mControlResumed = false;
//
//        }
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mResumed = true;
//        if (mFocused && mResumed && !mControlResumed) {
//            if(null != videoView) {
//                //videoView.resume();
//                mediaPlayer.stop();
//                videoView.seekTo(stopPosition);
//                videoView.start();
//            }
//            mControlResumed = true;
//        }
//    }
//
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        mFocused = hasFocus;
//        if (mFocused && mResumed && !mControlResumed) {
//            if (null != videoView) {
//                //videoView.resume();
//                videoView.seekTo(stopPosition);
//                videoView.start();
//            }
//            mControlResumed = true;
//
//        }
//    }

//        setContentView(R.layout.activity_insert_video_instruction);
//        submitbtn = (Button)findViewById(R.id.submitBtn);
//        myPref = this.getSharedPreferences("MyPref",MODE_PRIVATE);
//        videoview = findViewById(R.id.videoview);
//        VideoView video;
//        String video_url = "http://192.168.43.12/Artisans-Profiling/demo/BikasDas.mp4";
//        final ProgressDialog pd;
//
//        try {
//            TimeUnit.SECONDS.sleep(22);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        video = (VideoView)findViewById(R.id.videoview);
//
//        pd = new ProgressDialog(Insert_video_instructionActivity.this);
//        pd.setMessage("Buffering video please wait...");
//        pd.show();
//
//        Uri uri = Uri.parse(video_url);
//        video.setVideoURI(uri);
//        video.start();
//
//        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                //close the progress dialog when buffering is done
//                pd.dismiss();
//            }
//        });
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
