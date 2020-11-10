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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UpdateUserAuthActivity extends AppCompatActivity {
    TextInputLayout phone, name;
    EditText nam,ph;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String PhoneNoHolder, NameHolder;
    SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    String rowcount="0";

    String yesOrNo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_auth);

        phone = (TextInputLayout)findViewById(R.id.phone);
        name = (TextInputLayout)findViewById(R.id.name);
        nam = (EditText)findViewById(R.id.nam);
        ph = (EditText)findViewById(R.id.ph);
        submitbtn = (Button)findViewById(R.id.submitBtn);
        mediaPlayer = MediaPlayer.create(this, R.raw.nameandphoneinst);

        mediaPlayer.start();

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermission()) {

                    requestPermission();

                }
                else{
//                    mediaPlayer.start();
                    Log.d("Check", "onCreate: Okay");
                }
            }
        }

        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(UpdateUserAuthActivity.this);
        progressDialog = new ProgressDialog(UpdateUserAuthActivity.this);

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (!ph.getText().toString().equals("") && !nam.getText().toString().equals("")) {


                        regUser();


                        //UNCOMMENT THIS
                        mediaPlayer.stop();
                        Log.d("onclick e rowcount",rowcount);



                    } else {
                        ph.setError("ফোন নম্বর টাইপ করুন");
                    }
                }
                else{
                    Intent intent = new Intent(UpdateUserAuthActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }




        });
//        Intent i = new Intent(getApplicationContext(), NameActivity.class);
//        startActivity(i);
    }

    private void regUser() {

        progressDialog.setMessage("Fetching Data..");
        progressDialog.show();

        PhoneNoHolder = phone.getEditText().getText().toString().trim();
        NameHolder = name.getEditText().getText().toString().trim();

        //private boolean isValidMobile(String phone) {
//                    boolean flag = android.util.Patterns.PHONE.matcher(PhoneNoHolder).matches();
        //}

        Log.d("eirki phone->",PhoneNoHolder);
        myPref.edit().putString("phone", PhoneNoHolder).apply();
        myPref.edit().putString("count", "0").apply();

        PhoneNoHolder = PhoneNoHolder.replaceAll(" ","%20");
        NameHolder = NameHolder.replaceAll(" ","%20");


        String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
        PhoneNoHolder = PhoneNoHolder.replaceAll(characterFilter,"");
        NameHolder = NameHolder.replaceAll(characterFilter,"");

        String myurl = "https://artisanapp.xyz/checkUser.php?phone=" + PhoneNoHolder +
                "&name="+ NameHolder;
//                String myurl = "http://192.168.43.12/Artisans-Profiling/phoneno.php?phoneno=" + PhoneNoHolder;

//String myurl = "https://artisanapp.xyz/phoneno.php?phoneno=" + PhoneNoHolder;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        Log.d("eirki json", myurl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, myurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Hiding the progress dialog after all task complete.
                        Log.d("eirki dhukche", response);
                        showJSON(response);
                        progressDialog.dismiss();
                        // Showing response message coming from server.
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        myPref.edit().putString("track", "1").apply();
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
                        Toast.makeText(UpdateUserAuthActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                });
        queue.add(stringRequest);


    }

    private void showJSON(String response) {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            Log.d("eirki dhukche", response);
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            // for (int i = 0; i < result.length(); i++) {
            JSONObject jo = result.getJSONObject(0);
//                String title = jo.getString(Config5.KEY_TITLE);
//                String date = jo.getString(Config5.KEY_DATE);
//                String data = jo.getString(Config5.KEY_DATA);
            String id = jo.getString(Config.KEY_ID);
            rowcount = jo.getString(Config.ROW_COUNT);
            Log.d("json e rowcount",jo.getString(Config.ROW_COUNT));
            if (rowcount.equals("1")) {
                myPref.edit().putString("toUpdateFlag","1").apply();
                startActivity(new Intent(UpdateUserAuthActivity.this, FetchingDataActivity.class));
            }
            else{
                startActivity(new Intent(UpdateUserAuthActivity.this, UpdateUserAuthActivity.class));
                Toast.makeText(UpdateUserAuthActivity.this,
                        "আপনার এই নাম আর এই ফোন নম্বরের আগে কোনো রেকর্ড নেই ",Toast.LENGTH_LONG).show();
            }
            Log.d("eirki",id);
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
        int result = ContextCompat.checkSelfPermission(UpdateUserAuthActivity.this, Manifest.permission.CAMERA);

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
                            ActivityCompat.requestPermissions(UpdateUserAuthActivity.this,new String[]{Manifest.permission.CAMERA},666);
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
//                    mediaPlayer.start();

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
                                        Intent i = new Intent(UpdateUserAuthActivity.this, UpdateUserAuthActivity.class);
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
}