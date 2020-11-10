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

public class ProductSelectionActivity extends AppCompatActivity {
    Button saree, goina, bag, tshirt, wrapperskirt, palazzo, cushioncover, leatherbag, blousepiece, kurta, homedecor,
            utility, painting, stoles, handkerchief;
    private SharedPreferences myPref;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selection);

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

        myPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        mediaPlayer = MediaPlayer.create(this, R.raw.selectproductinst);

        mediaPlayer.start();
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            saree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "saree").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormSareeActivity.class));
                }
            });
            goina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "goina").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormGoinaActivity.class));
                }
            });
            bag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "bag").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormBagActivity.class));
                }
            });
            tshirt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "tshirt").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormTshirtActivity.class));
                }
            });


            wrapperskirt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormWrapperSkirtActivity.class));
                }
            });


            palazzo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormPalazzoActivity.class));
                }
            });


            cushioncover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormCushionCoverActivity.class));
                }
            });


            leatherbag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormLeatherbagActivity.class));
                }
            });


            blousepiece.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormBlousepieceActivity.class));
                }
            });

            kurta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "kurta").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormKurtaActivity.class));
                }
            });

            homedecor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "showpiece").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormShowpieceActivity.class));
                }
            });

            utility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormUtilityActivity.class));
                }
            });


            painting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormPaintingActivity.class));
                }
            });


            stoles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormStolesActivity.class));
                }
            });


            handkerchief.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myPref.edit().putString("selected", "other").apply();
                    myPref.edit().putString("track", "15").apply();
                    mediaPlayer.stop();
                    startActivity(new Intent(getApplicationContext(), FormHandkerchiefActivity.class));
                }
            });

        }
        else{
            Intent intent = new Intent(ProductSelectionActivity.this, InternetCheckActivity.class);
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