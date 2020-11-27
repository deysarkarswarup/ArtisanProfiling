package com.example.artisanprofilingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Intent i;
    SharedPreferences myPref;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        final String s = myPref.getString("track","0");

        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        if (s != null)
                        switch (s) {
                            case "0":
                                startActivity(new Intent(SplashScreen.this, NCoReIntoActivity.class));
//                                startActivity(new Intent(SplashScreen.this, FetchingDataActivity.class));
                                break;
                            case "1":
                                i = new Intent(SplashScreen.this, AddressActivity.class);
                                startActivity(i);
                                break;
                            case "2":
                                i = new Intent(SplashScreen.this, ProfilePicActivity.class);
                                startActivity(i);
                                break;
                            case "3":
                                i = new Intent(SplashScreen.this, ProfilePicActivity2.class);
                                startActivity(i);
                                break;
                            case "4":
                                i = new Intent(SplashScreen.this, ArtformActivity.class);
                                startActivity(i);
                                break;
                            case "5":
                                i = new Intent(SplashScreen.this, NewDirectionToCaptureImageActivity.class);
                                startActivity(i);
                                break;
                            case "6":
                                i = new Intent(SplashScreen.this, ImageCaptureSelection.class);
                                startActivity(i);
                                break;
                            case "7":
                                i = new Intent(SplashScreen.this, CaptureImageActivity.class);
                                startActivity(i);
                                break;
                            case "8":
                                i = new Intent(SplashScreen.this, CaptureImageActivity2.class);
                                startActivity(i);
                                break;
                            case "9":
                                i = new Intent(SplashScreen.this, CaptureImageActivity3.class);
                                startActivity(i);
                                break;
                            case "10":
                                i = new Intent(SplashScreen.this, CaptureImageActivity4.class);
                                startActivity(i);
                                break;
                            case "11":
                                i = new Intent(SplashScreen.this, AudioActivity.class);
                                startActivity(i);
                                break;
                            case "100":
                                i = new Intent(SplashScreen.this, WelcomeActivity.class);
                                startActivity(i);
                                break;
                        }

                }
                // }
            }, 3000);
        }
        else{
            Intent intent = new Intent(SplashScreen.this, InternetCheckActivity.class);
            startActivity(intent);
            finish();
        }
    }
}


