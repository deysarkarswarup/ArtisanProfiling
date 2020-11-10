package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EditPhoneActivity extends AppCompatActivity {
    TextInputLayout phno;
    EditText mobileno;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String PhoneNoHolder;
    SharedPreferences myPref;
    public static int REQUEST_PERMISSION=1;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone);
        mediaPlayer = MediaPlayer.create(this,R.raw.phoneno);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermission()) {

                    requestPermission();

                }
                else{

                    mediaPlayer.start();
                }
            }
        }


        phno = (TextInputLayout)findViewById(R.id.phoneno);
        mobileno = (EditText)findViewById(R.id.mobileNO);//to show error msg
        submitbtn = (Button)findViewById(R.id.submitBtn);

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(EditPhoneActivity.this);
        progressDialog = new ProgressDialog(EditPhoneActivity.this);
        final boolean[] flag = {true};
        //DELETE THIS
        // startActivity(new Intent(MainActivity.this, ArtformActivity3.class));
//        mobileno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(mobileno.getText().toString().length()!=10) {
//                    mobileno.setError("১০ ডিজিটের ফোন নম্বর টাইপ করুন");
//                    flag[0] = false;
//                }
//            }
//        });
        mobileno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mobileno.getText().toString().length()!=10) {
                    mobileno.setError("১০ ডিজিটের ফোন নম্বর টাইপ করুন");
                    flag[0] = false;
                }else{
                    //regUser();
                }
            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (!mobileno.getText().toString().equals("")) {

//                            if (!checkPermission()) {
//Log.d("hiii","hello");
//                                requestPermission();
//                            }

                        //Toast.makeText(MainActivity.this,"হয়েগেছে",Toast.LENGTH_LONG).show();
//                        if(flag[0]) {
                        regUser();


                        //UNCOMMENT THIS
                        mediaPlayer.stop();
                        startActivity(new Intent(EditPhoneActivity.this, UpdateSelectionAcivity.class));
//                        }

                    } else {
                        mobileno.setError("ফোন নম্বর টাইপ করুন");
                    }
                }
                else{
                    Intent intent = new Intent(EditPhoneActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }




        });
//        Intent i = new Intent(getApplicationContext(), NameActivity.class);
//        startActivity(i);
    }

    private void regUser() {

        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();

        PhoneNoHolder = phno.getEditText().getText().toString().trim();

        //private boolean isValidMobile(String phone) {
//                    boolean flag = android.util.Patterns.PHONE.matcher(PhoneNoHolder).matches();
        //}

        Log.d("eirki phone->",PhoneNoHolder);
//        myPref.edit().putString("phone", PhoneNoHolder).apply();
//        myPref.edit().putString("count", "0").apply();

        String idToGet = myPref.getString("id","No data found");


        PhoneNoHolder = PhoneNoHolder.replaceAll(" ","%20");

        String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
        PhoneNoHolder = PhoneNoHolder.replaceAll(characterFilter,"");

        String myurl = "https://artisanapp.xyz/updatePhone.php?id="+idToGet+ "&phone=" + PhoneNoHolder;
//                String myurl = "http://192.168.43.12/Artisans-Profiling/phoneno.php?phoneno=" + PhoneNoHolder;

//String myurl = "https://artisanapp.xyz/phoneno.php?phoneno=" + PhoneNoHolder;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Hiding the progress dialog after all task complete.
                        showJSON(response);
                        progressDialog.dismiss();
                        // Showing response message coming from server.
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
//                        myPref.edit().putString("track", "1").apply();
//                                Intent i = new Intent(getApplicationContext(), NameActivity.class);
//                                startActivity(i);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        // Showing error message if something goes wrong.
                        Toast.makeText(EditPhoneActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                });
        queue.add(stringRequest);


    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            // for (int i = 0; i < result.length(); i++) {
            JSONObject jo = result.getJSONObject(0);
//                String title = jo.getString(Config5.KEY_TITLE);
//                String date = jo.getString(Config5.KEY_DATE);
//                String data = jo.getString(Config5.KEY_DATA);
            String id = jo.getString(Config.KEY_ID);
//            Log.d("eirki",id);
            myPref.edit().putString("id",id).apply();


//                    final HashMap<String, String> employees = new HashMap<>();
////                employees.put(Config5.KEY_TITLE,  "Date = "+title);
////                employees.put(Config5.KEY_DATE, date);
////                employees.put(Config5.KEY_DATA, data);
//                    employees.put(Config.KEY_ID, id);
//
//                    list.add(employees);
//
//                    //}


        } catch (JSONException e) {
            e.printStackTrace();
        }
//                ListAdapter adapter = new SimpleAdapter(
//                        MainActivity.this, list, R.layout.activity_mylist,
//                        new String[]{Config5.KEY_ID},
//                        new int[]{R.id.tvid});
//
//                listview.setAdapter(adapter);


    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(EditPhoneActivity.this, Manifest.permission.CAMERA);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
//            requestPermission();
            return false;
        }
    }

    private void requestPermission() {
        int i=0;
//        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
//            i=1;
//            Toast.makeText(MainActivity.this, "Camera permission allows us to click images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is needed to access files from your device...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(EditPhoneActivity.this,new String[]{Manifest.permission.CAMERA},666);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},666);
        }
//
//        else {
//            i=3;
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
//
//        }
        Log.d("check",Integer.toString(i));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("value", "Permission Granted, Now you can use local drive .");
//                } else {
//                    Log.e("value", "Permission Denied, You cannot use local drive .");
//                }
//                break;
//        }

        switch (requestCode) {

            case 666: // Allowed was selected so Permission granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("Jhingalala", "granted");
                    mediaPlayer.start();

                    // do your work here

                } else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {
                    // User selected the Never Ask Again Option Change settings in app settings manually
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setTitle("Change Permissions in Settings");
                    alertDialogBuilder
                            .setMessage("" +
                                    "\nClick SETTINGS to Manually Set\n" + "Permissions to use Camera")
                            .setCancelable(false)
                            .setPositiveButton("SETTINGS", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, 1000);     // Comment 3.
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else {
                    // User selected Deny Dialog to EXIT App ==> OR <== RETRY to have a second chance to Allow Permissions
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setTitle("Second Chance");
                        alertDialogBuilder
                                .setMessage("Click RETRY to Set Permissions to Allow\n\n" + "Click EXIT to the Close App")
                                .setCancelable(false)
                                .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Integer.parseInt(WRITE_EXTERNAL_STORAGE));
                                        Intent i = new Intent(EditPhoneActivity.this, EditPhoneActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                    }
                                })
                                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finishAffinity();
                                        System.exit(0);
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
                break;
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