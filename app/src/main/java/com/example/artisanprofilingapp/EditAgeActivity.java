package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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

public class EditAgeActivity extends AppCompatActivity {
    TextInputLayout age;
    EditText nam, nam1, nam2, nam3,nam4, nam5;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String AddressHolder1;
    SharedPreferences myPref;
    int potaka=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_age);
        age = findViewById(R.id.age);
        nam1 = findViewById(R.id.nam1);//to show error msg
        submitbtn = findViewById(R.id.submitBtn);

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(EditAgeActivity.this);
        progressDialog = new ProgressDialog(EditAgeActivity.this);
        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (!nam1.getText().toString().equals("")) {
                        potaka =1;

                    } else {
                        nam1.setError("টাইপ করুন");
                    }
                    if (potaka==1){
                        regUser();
                        Intent i = new Intent(EditAgeActivity.this, FetchingDataActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(EditAgeActivity.this,"সব ফিল্ড টাইপ করুন",Toast.LENGTH_LONG).show();
                    }
                }

                else{
                    Intent intent = new Intent(EditAgeActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            private void regUser() {
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();
                AddressHolder1 = age.getEditText().getText().toString().trim();
                String idToGet = myPref.getString("id","No data found");
                AddressHolder1 = AddressHolder1.replaceAll(" ","%20");
                idToGet = idToGet.replaceAll(" ","%20");

                String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
                AddressHolder1 = AddressHolder1.replaceAll(characterFilter,"");
                idToGet = idToGet.replaceAll(characterFilter,"");

                String myurl = "https://artisanapp.xyz/edit_age.php?age="+ AddressHolder1+"&id="+ idToGet;

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, myurl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing response message coming from server.
                                Toast.makeText(EditAgeActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing error message if something goes wrong.
                                Toast.makeText(EditAgeActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                queue.add(stringRequest);

            }

        });

    }

    @Override
    public void onBackPressed() {
        myPref.edit().putString("track","100").apply();
        super.onBackPressed();
    }
    @Override
    public void onUserLeaveHint(){
        myPref.edit().putString("track","100").apply();
        super.onUserLeaveHint();
    }
}