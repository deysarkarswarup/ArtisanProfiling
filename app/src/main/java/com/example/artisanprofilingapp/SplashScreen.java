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
//    private BroadcastReceiver MyReceiver = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyReceiver = new MyReceiver();
        //String getconnectionresponse = myReceiver.status;
        //Log.d("oirki", getconnectionresponse);
//        getActivity().registerReceiver(receiver, filter);
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        final String s = myPref.getString("track","0");


        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
//            Intent intent = new Intent(SplashScreen.this, MapActivity.class);
//            startActivity(intent);
//            finish();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    startActivity(new Intent(SplashScreen.this, NCoReIntoActivity.class));
//        if (flag.equals("-1"))
//        {
//            i=new Intent(SplashScreen.this,InternetCheckActivity.class);
//            startActivity(i);
//        }
//        else {

                    if (s != null)
                        switch (s) {
                            case "0":
                                startActivity(new Intent(SplashScreen.this, NCoReIntoActivity.class));
//                                startActivity(new Intent(SplashScreen.this, FetchingDataActivity.class));
                                break;
//                            case "1":
//                                i = new Intent(SplashScreen.this, MainActivity.class);
//                                startActivity(i);
//                                break;
                            case "1":
//                                i = new Intent(SplashScreen.this, NameActivity.class);
                                startActivity(i);
                                break;
                            case "2":
//                                i = new Intent(SplashScreen.this, AgeActivity.class);
                                startActivity(i);
                                break;
                            case "3":
                                i = new Intent(SplashScreen.this, AddressActivity.class);
                                startActivity(i);
                                break;
                            case "4":
                                i = new Intent(SplashScreen.this, ArtformActivity.class);
                                startActivity(i);
                                break;
                            case "5":
//                                i = new Intent(SplashScreen.this, ArtformActivity2.class);
                                startActivity(i);
                                break;
                            case "6":
//                                i = new Intent(SplashScreen.this, ArtformActivity3.class);
                                startActivity(i);
                                break;
                            case "7":
//                                i = new Intent(SplashScreen.this, ExperienceActivity.class);
                                startActivity(i);
                                break;
                            case "8":
                                i = new Intent(SplashScreen.this, ImageCaptureSelection.class);
                                startActivity(i);
                                break;
                            case "9":
                                i = new Intent(SplashScreen.this, Insert_image_instructionActivity.class);
                                startActivity(i);
                                break;
                            case "10":
                                i = new Intent(SplashScreen.this, CaptureImageActivity.class);
                                startActivity(i);
                                break;
                            case "11":
                                i = new Intent(SplashScreen.this, CaptureImageActivity2.class);
                                startActivity(i);
                                break;
                            case "12":
                                i = new Intent(SplashScreen.this, CaptureImageActivity3.class);
                                startActivity(i);
                                break;
                            case "13":
                                i = new Intent(SplashScreen.this, CaptureImageActivity4.class);
                                startActivity(i);
                                break;
                            case "14":
//                                i = new Intent(SplashScreen.this, ProductSelectionActivity.class);
                                startActivity(i);
                                break;
//                            case "16":
//                                i = new Intent(SplashScreen.this, .class);
//                                startActivity(i);
//                                break;
                            case "16":
                                i = new Intent(SplashScreen.this, AudioActivity.class);
                                startActivity(i);
                                break;
                            case "17":
//                                i = new Intent(SplashScreen.this, UserChoiceActivity.class);
                                startActivity(i);
                                break;
//                            case "13":
//                                i = new Intent(SplashScreen.this, Insert_video_instructionActivity.class);
//                                startActivity(i);
//                                break;
//                            case "14":
//                                i = new Intent(SplashScreen.this, CaptureVideoActivity.class);
//                                startActivity(i);
//                                break;
//                            case "15":
//                                i = new Intent(SplashScreen.this, Insert_Artfrom_video_instructionActivity.class);
//                                startActivity(i);
//                                break;
//                            case "16":
//                                i = new Intent(SplashScreen.this, CaptureArtformVideoActivity.class);
//                                startActivity(i);
//                                break;
                            case "18":
                                i = new Intent(SplashScreen.this, ThankYouActivity.class);
                                startActivity(i);
                                break;
                            default:
//                                i = new Intent(SplashScreen.this, UserTypeActivity.class);
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



//        Log.d("initially eirki->", s);
//         String res = broadcastIntent();
//MyReceiver myReceive = new MyReceiver();
//final String flag = myReceive.status;
//        Log.d("after eirki->", flag);
//        Log.d("after eirki->", res);

//        startActivity(new Intent(this,MainActivity.class));
    }

//    public String broadcastIntent() {
//        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        String TrackToGet = myPref.getString("track","No data found");
//        return TrackToGet;
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(MyReceiver);
//    }
}


