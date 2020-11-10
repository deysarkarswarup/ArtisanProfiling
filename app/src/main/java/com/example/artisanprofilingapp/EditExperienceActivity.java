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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class EditExperienceActivity extends AppCompatActivity {
    TextInputLayout exp;
    EditText nam;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String ExpHolder;
    SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_experience);

        exp = (TextInputLayout)findViewById(R.id.experi);
        nam = (EditText)findViewById(R.id.nam);//to show error msg
        submitbtn = (Button)findViewById(R.id.submitBtn);
        mediaPlayer = MediaPlayer.create(this, R.raw.editexpinst);

        mediaPlayer.start();

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(EditExperienceActivity.this);
        progressDialog = new ProgressDialog(EditExperienceActivity.this);

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (!nam.getText().toString().equals("")) {
                        //Toast.makeText(MainActivity.this,"হয়েগেছে",Toast.LENGTH_LONG).show();
                        ExpHolder = exp.getEditText().getText().toString().trim();
                        Log.d("eirki name->", ExpHolder);
                        //myPref.edit().putString("phone", PhoneNoHolder).apply();
//                        myPref.edit().putString("name", NameHolder).apply();
                        regUser();
//                        myPref.edit().putString("track", "2").apply();
                        mediaPlayer.stop();
                        Intent i = new Intent(EditExperienceActivity.this, UpdateSelectionAcivity.class);
                        startActivity(i);
                    } else {
                        nam.setError("আপনার নাম টাইপ করুন");
                    }
                }
                else{
                    Intent intent = new Intent(EditExperienceActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            private void regUser() {
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();

                ExpHolder = exp.getEditText().getText().toString().trim();
                Log.d("eirki name->",ExpHolder);
                //myPref.edit().putString("phone", PhoneNoHolder).apply();
//                myPref.edit().putString("name",ExpHolder).apply();

                //String dataToGet = myPref.getString("phone","No data found");
                String dataToGet = myPref.getString("id","No data found");
                Log.d("eirki id->",dataToGet);

                ExpHolder = ExpHolder.replaceAll(" ","%20");

                String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
                ExpHolder = ExpHolder.replaceAll(characterFilter,"");

                //String myurl = "http://192.168.43.12/Artisans-Profiling/name.php?name=" + NameHolder +"&phoneno="+ dataToGet;
                String myurl = "https://artisanapp.xyz/updateExperience.php?id=" + dataToGet +"&exp="+ ExpHolder;
                Log.d("myurl eirki", "regUser: "+myurl);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing response message coming from server.
                                Toast.makeText(EditExperienceActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
//                                myPref.edit().putString("track", "2").apply();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing error message if something goes wrong.
                                Toast.makeText(EditExperienceActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();

                            }
                        });
                queue.add(stringRequest);

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