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

public class EditAddressActivity extends AppCompatActivity {
    TextInputLayout name, age, addressLine2, landMark, pinCode, addressExp;
    EditText nam, nam1, nam2, nam3,nam4, nam5;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String AddressHolder2, AddressHolder3, AddressHolder4;
    SharedPreferences myPref;
    RadioGroup radioGroup1, radioGroup2;
    String gender, caste;
    int potaka=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        addressLine2 = findViewById(R.id.addressLine2);
        pinCode = findViewById(R.id.pinCode);
        landMark = findViewById(R.id.landMark);
        addressExp = findViewById(R.id.addressexp);


        nam = findViewById(R.id.nam);//to show error msg
        nam1 = findViewById(R.id.nam1);//to show error msg
        nam2 = findViewById(R.id.nam2);//to show error msg
        nam3 = findViewById(R.id.nam3);//to show error msg
        nam4 = findViewById(R.id.nam4);//to show error msg
        nam5 = findViewById(R.id.nam5);
        submitbtn = findViewById(R.id.submitBtn);

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        myPref.edit().putString("track","100").apply();
        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(EditAddressActivity.this);
        progressDialog = new ProgressDialog(EditAddressActivity.this);

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    if (!nam2.getText().toString().equals("")) {
                        potaka=1;

                    } else {
                        nam2.setError("টাইপ করুন");
                    }
                    if (!nam3.getText().toString().equals("")) {
                        potaka++;

                    } else {
                        nam3.setError("টাইপ করুন");
                    }
                    if (!nam4.getText().toString().equals("")) {
                        potaka++;

                    } else {
                        nam4.setError("টাইপ করুন");
                    }
                    if (potaka>=3){
                        regUser();
                        Intent i = new Intent(EditAddressActivity.this, FetchingDataActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(EditAddressActivity.this,"সব ফিল্ড টাইপ করুন",Toast.LENGTH_LONG).show();
                    }
                }

                else{
                    Intent intent = new Intent(EditAddressActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            private void regUser() {
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();
                AddressHolder2 = addressLine2.getEditText().getText().toString().trim();
                AddressHolder3 = pinCode.getEditText().getText().toString().trim();
                AddressHolder4 = landMark.getEditText().getText().toString().trim();
                String idToGet = myPref.getString("id","No data found");
                AddressHolder2 = AddressHolder2.replaceAll(" ","%20");
                AddressHolder3 = AddressHolder3.replaceAll(" ","%20");
                AddressHolder4 = AddressHolder4.replaceAll(" ","%20");
                idToGet = idToGet.replaceAll(" ","%20");

                String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
                AddressHolder2 = AddressHolder2.replaceAll(characterFilter,"");
                AddressHolder3 = AddressHolder3.replaceAll(characterFilter,"");
                AddressHolder4 = AddressHolder4.replaceAll(characterFilter,"");
                idToGet = idToGet.replaceAll(characterFilter,"");

                String myurl = "https://artisanapp.xyz/edit_address.php?addressLine2="+ AddressHolder2 +"&pinCode="+ AddressHolder3
                        +"&landMark="+ AddressHolder4+"&id="+ idToGet;

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, myurl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing response message coming from server.
                                Toast.makeText(EditAddressActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();
                                // Showing error message if something goes wrong.
                                Toast.makeText(EditAddressActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
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