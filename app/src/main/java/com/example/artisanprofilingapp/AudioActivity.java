package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AudioActivity extends AppCompatActivity {
    TextInputLayout name;
    EditText nam;//to show error msg
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio ;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    RequestQueue requestQueue;
    MediaPlayer mediaPlayer ;
    ProgressDialog progressDialog;
    SharedPreferences myPref;
//    private File file;
  //  private Uri file_uri, uri;
    private String dataToGet;
    String fileName="", PriceHolder="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        name = (TextInputLayout)findViewById(R.id.name);
        nam = (EditText)findViewById(R.id.nam);//to show error msg

        buttonStart = (Button) findViewById(R.id.button1);
        buttonStop = (Button) findViewById(R.id.button2);
        buttonPlayLastRecordAudio = (Button) findViewById(R.id.button3);

        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        dataToGet = myPref.getString("id","No data found");
        Log.d("hiii", "onCreate: "+dataToGet);
        mediaPlayer = MediaPlayer.create(this, R.raw.audioinst);

        mediaPlayer.start();

        requestQueue = Volley.newRequestQueue(AudioActivity.this);
        progressDialog = new ProgressDialog(AudioActivity.this);

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

                            Toast.makeText(AudioActivity.this, "Recording started",
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent intent = new Intent(AudioActivity.this, InternetCheckActivity.class);
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

                Toast.makeText(AudioActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
    //            buttonStopPlayingRecording.setEnabled(true);
                mediaPlayer = new MediaPlayer();
                try {
                    fileName = AudioSavePathInDevice;
                    if (!nam.getText().toString().equals("")) {
                        PriceHolder = name.getEditText().getText().toString().trim();
                        Log.d("hmm Priczz", PriceHolder);
                    } else {
                        nam.setError("আপনার প্রোডাক্টের দাম বলুন");
                    }
                    uploadAudio();
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
//                    mediaPlayer.prepare();
                    Log.d("hmm",AudioSavePathInDevice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //mediaPlayer.start();
//                Toast.makeText(AudioActivity.this, "Recording Playing",
//                        Toast.LENGTH_LONG).show();
            }
        });



//        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                buttonStop.setEnabled(false);
//                buttonStart.setEnabled(true);
//                buttonStopPlayingRecording.setEnabled(false);
//                buttonPlayLastRecordAudio.setEnabled(true);
//                if(mediaPlayer != null){
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    MediaRecorderReady();
//                }
//            }
//        });
    }

    private void uploadAudio() {
        class UploadAudio extends AsyncTask<Void, Void, String> {

            ProgressDialog uploading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                uploading = ProgressDialog.show(AudioActivity.this, "Uploading File", "Please wait...", false, false);
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                uploading.dismiss();
                Toast.makeText(AudioActivity.this, s, Toast.LENGTH_LONG).show();
//                    textViewResponse.setText(Html.fromHtml("<b>Uploaded at <a href='" + s + "'>" + s + "</a></b>"));
//                    textViewResponse.setMovementMethod(LinkMovementMethod.getInstance());
                mediaPlayer.stop();
                myPref.edit().putString("track", "17").apply();
                Intent i=new Intent(AudioActivity.this,UserChoiceActivity.class);
                startActivity(i);
            }



            @Override
            protected String doInBackground(Void... params) {
                AudioUpload u = new AudioUpload();
                String msg = u.upLoad2Server(fileName,dataToGet+"*"+PriceHolder);
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

    private void requestPermission() {
        ActivityCompat.requestPermissions(AudioActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(AudioActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AudioActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
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
