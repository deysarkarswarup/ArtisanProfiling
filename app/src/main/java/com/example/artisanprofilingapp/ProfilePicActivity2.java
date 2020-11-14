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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ProfilePicActivity2 extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private Button button,yes,no,buttonStart,buttonStop,buttonPlayLastRecordAudio;
    private String encoded_string, image_name;
    int count = 0;
    private Bitmap bitmap;
    private File file;
    TextView inst;
    private Uri file_uri;
    SharedPreferences myPref;
    private String dataToGet, idToGet;
    private String ImageCountToGet;
    private MediaPlayer mediaPlayer;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String fileName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic2);
        button = (Button) findViewById(R.id.start);
        yes = (Button) findViewById(R.id.yes) ;
        no = (Button) findViewById(R.id.no) ;
        buttonStart = (Button) findViewById(R.id.button1) ;
        buttonStop = (Button) findViewById(R.id.button2) ;
        buttonPlayLastRecordAudio = (Button) findViewById(R.id.next) ;
        inst = findViewById(R.id.inst);

        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        dataToGet = myPref.getString("phone","No data found");
        idToGet = myPref.getString("id","No data found");
        ImageCountToGet = myPref.getString("count","No data found");
//        mediaPlayer = MediaPlayer.create(this, R.raw.profilepicinst);
//        mediaPlayer.start();

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermission()) {

                    requestPermission();
                }
            }
        }
//        mediaPlayer.start();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mediaPlayer.start();
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    getFileUri();

                    count = Integer.parseInt(ImageCountToGet) + 1;
                    Log.d("count", String.valueOf(count));
                    ImageCountToGet = String.valueOf(count);
                    myPref.edit().putString("count", ImageCountToGet).apply();
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                    startActivityForResult(i, 10);
                }
                else{
                    Intent intent = new Intent(ProfilePicActivity2.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mediaPlayer.start();
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    getFileUri();

                    count = Integer.parseInt(ImageCountToGet) + 1;
                    Log.d("count", String.valueOf(count));
                    ImageCountToGet = String.valueOf(count);
                    myPref.edit().putString("count", ImageCountToGet).apply();
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                    startActivityForResult(i, 10);
                }
                else{
                    Intent intent = new Intent(ProfilePicActivity2.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mediaPlayer.start();
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    inst.setVisibility(View.VISIBLE);
                    buttonStart.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                    buttonPlayLastRecordAudio.setVisibility(View.VISIBLE);

                }
                else{
                    Intent intent = new Intent(ProfilePicActivity2.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        requestQueue = Volley.newRequestQueue(ProfilePicActivity2.this);
        progressDialog = new ProgressDialog(ProfilePicActivity2.this);

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);

        random = new Random();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkPermission()) {

                    ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = con.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                CreateRandomAudioFileName(5) + "AudioRecording.3gp";

                        MediaRecorderReady();

                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IllegalStateException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        buttonStart.setEnabled(false);
                        buttonStop.setEnabled(true);

                        Toast.makeText(ProfilePicActivity2.this, "Recording started",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(ProfilePicActivity2.this, InternetCheckActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    requestPermission();
                }
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);

                Toast.makeText(ProfilePicActivity2.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                mediaPlayer = new MediaPlayer();
                try {
                    fileName = AudioSavePathInDevice;

                    uploadAudio();
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    Log.d("hmm",AudioSavePathInDevice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //mediaPlayer.start();
            }
        });

    }

    private void getFileUri() {
        image_name = "_"+ dataToGet + ".jpg";

        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + count + image_name);
        Log.d("msg" , String.valueOf(file));
        file_uri = Uri.fromFile(file);
        Log.d("msg" , String.valueOf(file_uri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            new ProfilePicActivity2.Encode_image().execute();
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    private class Encode_image extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            bitmap = BitmapFactory.decodeFile(file_uri.getPath());
            ExifInterface ei = null;
            try {
                ei = new ExifInterface(Objects.requireNonNull(file_uri.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert ei != null;
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            Bitmap rotatedBitmap = null;
            switch(orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmap;
            }
            bitmap = rotatedBitmap;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);//compress image as per ur need
            bitmap.recycle();

            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array, 0);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            makeRequest();
            Toast.makeText(ProfilePicActivity2.this, "picture submitted successfully!", Toast.LENGTH_LONG).show();
            myPref.edit().putString("track", "7").apply();
//            mediaPlayer.stop();
            Intent i=new Intent(ProfilePicActivity2.this, ProfilePicActivity2.class);
            startActivity(i);
        }
    }

    private void makeRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
      StringRequest request = new StringRequest(Request.Method.POST, "https://artisanapp.xyz/profilepic.php",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("encoded_string",encoded_string);
                map.put("image_name",count+image_name);
                map.put("id",idToGet);

                return map;
            }
        };
        requestQueue.add(request);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(ProfilePicActivity2.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {


            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(ProfilePicActivity2.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ProfilePicActivity2.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void uploadAudio() {
        class UploadAudio extends AsyncTask<Void, Void, String> {

            ProgressDialog uploading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                uploading = ProgressDialog.show(ProfilePicActivity2.this, "Uploading File", "Please wait...", false, false);
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                uploading.dismiss();
                Toast.makeText(ProfilePicActivity2.this, s, Toast.LENGTH_LONG).show();
                mediaPlayer.stop();
                myPref.edit().putString("track", "17").apply();
                Intent i=new Intent(ProfilePicActivity2.this,ArtformActivity.class);
                startActivity(i);
            }



            @Override
            protected String doInBackground(Void... params) {
                ProfileAudioUpload u = new ProfileAudioUpload();
                String msg = u.upLoad2Server(fileName,idToGet);
                Log.d("hmm msg-->" ,msg);
                return msg;

            }
        }
        UploadAudio uv = new UploadAudio();
        uv.execute();
    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));
            i++ ;
        }
        return stringBuilder.toString();
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