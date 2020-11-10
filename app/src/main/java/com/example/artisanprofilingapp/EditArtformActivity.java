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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class EditArtformActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextInputLayout artform;
    EditText nam;//to show error msg
    Button submitbtn;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String ArtformHolder;
    SharedPreferences myPref;
    private MediaPlayer mediaPlayer;
    String[] artFormName = new String[10];
    Spinner spin;
    Boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artform);

        //artform = (TextInputLayout)findViewById(R.id.artform);
        spin = (Spinner) findViewById(R.id.spinner);
        nam = (EditText)findViewById(R.id.nam);//to show error msg
        submitbtn = (Button)findViewById(R.id.submitBtn);
        mediaPlayer = MediaPlayer.create(this, R.raw.artforminst);

        mediaPlayer.start();

        //Initialize of SharedPref
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(EditArtformActivity.this);
        progressDialog = new ProgressDialog(EditArtformActivity.this);
        getData();

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (flag) {
                        //Toast.makeText(MainActivity.this,"হয়েগেছে",Toast.LENGTH_LONG).show();
                        //regUser();
                        mediaPlayer.stop();
                        Intent i = new Intent(EditArtformActivity.this, EditArtformActivity2.class);
                        startActivity(i);
                    } else {
                        nam.setError("আপনি কোন শিল্প নিয়ে কাজ করেন?");
                    }
                }
                else{
                    Intent intent = new Intent(EditArtformActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }



        });

    }

    private void getData() {
//        String url ="http://192.168.43.12/Artisans-Profiling/artform.php";
        String url ="https://artisanapp.xyz/UpdateArtform.php";

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                myPref.edit().putString("track", "5").apply();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditArtformActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response)  {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
//                String iname = jo.getString(Config5.IName);
//                String itel = jo.getString(Config5.Itel);
//                String data = jo.getString(Config5.KEY_DATA);
                //String id = jo.getString(Config5.KEY_ID);
                artFormName[i] = jo.getString(Config.ArtFormName);
                Log.d("hmm i->", String.valueOf(i));
                Log.d("hmm id->",artFormName[i]);
//            Log.d("hmm id->",id);
//                Log.d("hmm iname->",iname);
//                Log.d("hmm itel->",itel);



                final HashMap<String, String> artfrm = new HashMap<>();
                artfrm.put(Config.ArtFormName, artFormName[i]);
//                employees.put(Config5.IName,  iname);
//                employees.put(Config5.Itel, itel);
////                employees.put(Config5.KEY_DATA, data);
//                employees.put(Config5.KEY_ID, id);

                list.add(artfrm);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
//        ListAdapter adapter = new SimpleAdapter(
//                MainActivity.this, list, R.layout.activity_mylist,
////                new String[]{Config5.IName,Config5.Itel,Config5.KEY_ID},
////                new int[]{R.id.iname, R.id.itel,R.id.tvid});
//
//                new String[]{Config5.ArtFormName},
//                new int[]{R.id.tvid});
//        Log.d("hmm listview", Arrays.toString(artFormName));
//       listview.setAdapter(adapter);
        ArrayList<String> list1 = new ArrayList<String>();
        int j=0;

        for(int i=0;i<artFormName.length;i++){
            if (artFormName[i]!=null) {
                list1.add(artFormName[i]);
//                Log.d("dekha", "showJSON: " + art[j - 1]);
            }
        }
        artFormName = list1.toArray(new String[list1.size()]);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.spinner_layout, artFormName);
        adapter1.setDropDownViewResource(R.layout.spinner_layout);
//        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,artFormName,R.layout.spinner_layout);
        spin.setAdapter(adapter1);
        spin.setOnItemSelectedListener(this);

    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        // Toast.makeText(getApplicationContext(), "Selected User: "+artFormName[position] ,Toast.LENGTH_SHORT).show();
        myPref.edit().putString("artform",artFormName[position]).apply();
        flag = true;

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }


//    private void regUser() {
//
//            progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
//            progressDialog.show();
//
//            ArtformHolder = artform.getEditText().getText().toString().trim();
//            Log.d("eirki",ArtformHolder);
//            //myPref.edit().putString("phone", PhoneNoHolder).apply();
//            String dataToGet = myPref.getString("phone","No data found");
//            Log.d("eirki",dataToGet);
//
//            String myurl = "http://192.168.43.12/Artisans-Profiling/artform.php?artform=" + ArtformHolder +"&phoneno="+ dataToGet;
//
//
//
//            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String ServerResponse) {
//                            // Hiding the progress dialog after all task complete.
//                            progressDialog.dismiss();
//
//                            // Showing response message coming from server.
//                            Toast.makeText(ArtformActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
//                            myPref.edit().putString("track", "4").apply();
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            // Hiding the progress dialog after all task complete.
//                            progressDialog.dismiss();
//                            // Showing error message if something goes wrong.
//                            Toast.makeText(ArtformActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
//
//                        }
//                    });
//            queue.add(stringRequest);
//
//
//    }

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