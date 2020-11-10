package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class EditArtformActivity2 extends AppCompatActivity {
    TextInputLayout artlearned;
    EditText nam;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String ArtLearnedHolder;
    SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artform2);

        artlearned = (TextInputLayout)findViewById(R.id.artlearned);
        nam = (EditText)findViewById(R.id.nam);//to show error msg
        submitbtn = (Button)findViewById(R.id.submitBtn);
        mediaPlayer = MediaPlayer.create(this, R.raw.artform2inst);

        mediaPlayer.start();

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        // Creating Volley newRequestQueue .
//        requestQueue = Volley.newRequestQueue(ArtformActivity2.this);
        progressDialog = new ProgressDialog(EditArtformActivity2.this);

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (!nam.getText().toString().equals("")) {
                        //Toast.makeText(MainActivity.this,"হয়েগেছে",Toast.LENGTH_LONG).show();
                        //regUser();
                        ArtLearnedHolder = artlearned.getEditText().getText().toString().trim();
                        Log.d("eirki artlearned stor->", ArtLearnedHolder);
                        myPref.edit().putString("artlearned", ArtLearnedHolder).apply();
//                        myPref.edit().putString("track", "6").apply();
                        mediaPlayer.stop();
                        Intent i = new Intent(EditArtformActivity2.this, EditArtformActivity3.class);
                        startActivity(i);
                    } else {
                        nam.setError("আপনি কোথা থেকে এই শিল্প শিখেছেন?");
                    }
                }
                else{
                    Intent intent = new Intent(EditArtformActivity2.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

//            private void regUser() {
//                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
//                progressDialog.show();
//
//                ExperienceHolder = experience.getEditText().getText().toString().trim();
//                Log.d("eirki",ExperienceHolder);
//                //myPref.edit().putString("phone", PhoneNoHolder).apply();
//                String dataToGet = myPref.getString("phone","No data found");
//                String artformToGet = myPref.getString("artform","No data found");
//                Log.d("eirki phone->",dataToGet);
//                Log.d("eirki artform->",artformToGet);
//
//                String myurl = "http://192.168.43.12/Artisans-Profiling/experience.php?experience=" + ExperienceHolder +"&phoneno="+ dataToGet;
//
//                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String ServerResponse) {
//                                // Hiding the progress dialog after all task complete.
//                                progressDialog.dismiss();
//                                // Showing response message coming from server.
//                                Toast.makeText(ArtformActivity2.this, ServerResponse, Toast.LENGTH_LONG).show();
//                                myPref.edit().putString("track", "5").apply();
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError volleyError) {
//                                // Hiding the progress dialog after all task complete.
//                                progressDialog.dismiss();
//                                // Showing error message if something goes wrong.
//                                Toast.makeText(ArtformActivity2.this, volleyError.toString(), Toast.LENGTH_LONG).show();
//
//                            }
//                        });
//                queue.add(stringRequest);
//
//            }

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



