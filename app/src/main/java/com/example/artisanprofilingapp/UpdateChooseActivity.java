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

public class UpdateChooseActivity extends AppCompatActivity {
    Button age, address, profilePic, expe, silpo, newProduct;
    private SharedPreferences myPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_choose);

        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        profilePic = findViewById(R.id.profilePic);
        expe = findViewById(R.id.expe);
        silpo = findViewById(R.id.silpo);
        newProduct = findViewById(R.id.newProduct);

        myPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        myPref.edit().putString("updateProfile","1").apply();
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            age.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), EditAgeActivity.class));
                }
            });

            address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), EditAddressActivity.class));
                }
            });

            profilePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), ProfilePicActivity.class));
                }
            });

            expe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), EditExperienceActivity.class));
                }
            });

            silpo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), EditArtformActivity.class));
                }
            });

            newProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), ImageCaptureSelection.class));
                }
            });

        }
        else{
            Intent intent = new Intent(UpdateChooseActivity.this, InternetCheckActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public void onUserLeaveHint(){
        super.onUserLeaveHint();
    }

}