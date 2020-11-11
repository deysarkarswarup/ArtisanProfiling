package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

public class EditArtformActivity extends AppCompatActivity {
    TextInputLayout experi;
    EditText nam;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String DataHolder;
    SharedPreferences myPref;
    //    private MediaPlayer mediaPlayer;
    RadioGroup radioGroup, radioGroup1, radioGroup2;
    String yesOrNo, yesOrNo1,typeOfSilpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artform);

//        experi = findViewById(R.id.experi);
//        nam = findViewById(R.id.nam);//to show error msg

        submitbtn = findViewById(R.id.submitBtn);

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(EditArtformActivity.this);
        progressDialog = new ProgressDialog(EditArtformActivity.this);
//        mediaPlayer = MediaPlayer.create(this, R.raw.addressinst);
//
//        mediaPlayer.start();

//        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.yes:
//                        yesOrNo = "হ্যাঁ";
//                        break;
//                    case R.id.no:
//                        yesOrNo = "না";
//                        break;
//                }
//            }
//        });

        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        typeOfSilpo = "কাঁথাস্টিচ";
                        break;
                    case R.id.radio2:
                        typeOfSilpo = "বাটিক প্রিন্ট";
                        break;
                    case R.id.radio3:
                        typeOfSilpo = "চামড়ার কাজ";
                        break;
                    case R.id.radio4:
                        typeOfSilpo = "জুয়েলারি";
                        break;
                    case R.id.radio5:
                        typeOfSilpo = "অন্যান্য শিল্প কর্ম";
                        break;
                }
            }
        });

//        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
//        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.yes1:
//                        yesOrNo1 = "হ্যাঁ";
//                        break;
//                    case R.id.no1:
//                        yesOrNo1 = "না";
//                        break;
//
//                }
//            }
//        });

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
//                    if (!nam.getText().toString().equals("")) {
                        regUser();
////                        mediaPlayer.stop();
                        Intent i = new Intent(EditArtformActivity.this, FetchingDataActivity.class);
//                        startActivity(i);
//
//                    } else {
//                        nam.setError("টাইপ করুন");
//                    }

                }

                else{
                    Intent intent = new Intent(EditArtformActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            private void regUser() {
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();

//                DataHolder = experi.getEditText().getText().toString().trim();

                String idToGet = myPref.getString("id","No data found");

//                DataHolder = DataHolder.replaceAll(" ","%20");

                idToGet = idToGet.replaceAll(" ","%20");

                String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
//                DataHolder = DataHolder.replaceAll(characterFilter,"");

                idToGet = idToGet.replaceAll(characterFilter,"");

                String myurl = "https://artisanapp.xyz/edit_artform.php?typeOfSilpo=" + typeOfSilpo+"&id="+idToGet;

                Log.d("msg",myurl);

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, myurl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing response message coming from server.
                                Toast.makeText(EditArtformActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                                myPref.edit().putString("track", "4").apply();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing error message if something goes wrong.
                                Toast.makeText(EditArtformActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                queue.add(stringRequest);

            }

        });


    }

    @Override
    public void onBackPressed() {
//        mediaPlayer.stop();
        super.onBackPressed();
    }
    @Override
    public void onUserLeaveHint(){
//        mediaPlayer.stop();
        super.onUserLeaveHint();
    }
}