package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.iceteck.silicompressorr.SiliCompressor;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class CaptureVideoActivity extends AppCompatActivity {
    private Button capture;
    private File file;
    private Uri file_uri, uri;
    static final int REQUEST_VIDEO_CAPTURE = 101;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private String video_name;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    SharedPreferences myPref;
    private String dataToGet;
    private MediaPlayer mediaPlayer;
private String outputVideoPath="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_video);
        capture = findViewById(R.id.capture);

        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        dataToGet = myPref.getString("id","No data found");
        Log.d("hiii", "onCreate: "+dataToGet);
        mediaPlayer = MediaPlayer.create(this, R.raw.profilevideocaptureinst);

        mediaPlayer.start();

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(CaptureVideoActivity.this);
        progressDialog = new ProgressDialog(CaptureVideoActivity.this);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    captureVideo();
                }
                else{
                    Intent intent = new Intent(CaptureVideoActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void captureVideo(){
        Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(i.resolveActivity(getPackageManager())!=null){
            if (!checkPermission()) {
                requestPermission(); // Code for permission
            }

//            getFileUri();
//            i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
            startActivityForResult(i,REQUEST_VIDEO_CAPTURE);
//            makeRequest();
        }
    }

//    private void getFileUri() {
//        //img_type = "yes";
//        video_name = dataToGet+"_intro.mp4";
//        //img_type = ".jpg";
//        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
//                + File.separator + video_name
//        );
//        Log.d("hmm" , String.valueOf(file));
//        file_uri = Uri.fromFile(file);
//        Log.d("hmm" , String.valueOf(file_uri));
//        Log.d("hmm" , String.valueOf(file_uri.toString()));
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode==RESULT_OK){
            Toast.makeText(this,"Video Captured Properly",Toast.LENGTH_SHORT).show();
            //makeRequest();
            uri = intent.getData();
            file = new File(getRealPathFromURI(uri));
            Log.d("hmm", file.toString());

            String inputVideoPath = file.toString();
            Log.d("doFileUpload ", inputVideoPath);
//            FFmpeg ffmpeg = FFmpeg.getInstance(this);
//            try {
//                //Load the binary
//                ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onFailure() {
//                    }
//
//                    @Override
//                    public void onSuccess() {
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//                });
//            } catch (FFmpegNotSupportedException e) {
//                // Handle if FFmpeg is not supported by device
//            }
//            try {
//                // to execute "ffmpeg -version" command you just need to pass "-version"
//                outputVideoPath = inputVideoPath;
//                String[] commandArray = new String[]{};
//                commandArray = new String[]{"-y","-i", inputVideoPath, outputVideoPath};
////                $ ffmpeg -i input.mp4 -vcodec h264 -acodec mp2 output.mp4
//                //final ProgressDialog dialog = new ProgressDialog(CaptureVideoActivity.this);
//                ffmpeg.execute(commandArray, new ExecuteBinaryResponseHandler() {
//                    @Override
//                    public void onStart() {
//                        Log.e("FFmpeg", "onStart");
//                        //dialog.setMessage("Compressing... please wait");
//                        //dialog.show();
//                    }
//                    @Override
//                    public void onProgress(String message) {
//                        Log.e("FFmpeg onProgress? ", message);
//                    }
//                    @Override
//                    public void onFailure(String message) {
//                        Log.e("FFmpeg onFailure? ", message);
//                    }
//                    @Override
//                    public void onSuccess(String message) {
//                        Log.e("FFmpeg onSuccess? ", message);
//
//                    }
//                    @Override
//                    public void onFinish() {
//                        Log.e("FFmpeg", "onFinish");
////                        if (dialog.isShowing())
////                            dialog.dismiss();
//////                        playVideoOnVideoView(Uri.parse(outputPath));
//                        //isCompressed = true;
//                        //count = count + 1;
//                    }
//                });
//            } catch (FFmpegCommandAlreadyRunningException e) {
//                e.printStackTrace();
//                // Handle if FFmpeg is already running
//            }

//            try {
//                outputVideoPath = SiliCompressor.with(getApplicationContext()).compressVideo(inputVideoPath,Environment.DIRECTORY_DCIM);
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
            uploadVideo();
//            myPref.edit().putString("track", "14").apply();

        }
    }

// bhai php file to filename thik asche... echo te to thik e dekhache  dara daraaccha
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(CaptureVideoActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to save files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
//    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
//        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) {
//            return contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
//            return cursor.getString(idx);
//        }
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Video.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
//    public void makeRequest(){
//        String myurl = "http://192.168.43.12/Artisans-Profiling/videoupload.php";
//
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String ServerResponse) {
//                        // Hiding the progress dialog after all task complete.
//                        progressDialog.dismiss();
//                        // Showing response message coming from server.
//                        Toast.makeText(CaptureVideoActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        // Hiding the progress dialog after all task complete.
//                        progressDialog.dismiss();
//                        // Showing error message if something goes wrong.
//                        Toast.makeText(CaptureVideoActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
//
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> map = new HashMap<>();
//                map.put("file_path",String.valueOf(file_uri));
//                map.put("video_name",video_name);
//
//                return map;
//            }
//        };
//        queue.add(stringRequest);

    private void uploadVideo() {
        class UploadVideo extends AsyncTask<Void, Void, String> {

            ProgressDialog uploading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //uploading = ProgressDialog.show(CaptureVideoActivity.this, "Uploading File", "Please wait...", false, false);
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                uploading.dismiss();
                Toast.makeText(CaptureVideoActivity.this, s, Toast.LENGTH_LONG).show();
//                    textViewResponse.setText(Html.fromHtml("<b>Uploaded at <a href='" + s + "'>" + s + "</a></b>"));
//                    textViewResponse.setMovementMethod(LinkMovementMethod.getInstance());
//                myPref.edit().putString("track", "10").apply();
                mediaPlayer.stop();
                Intent i=new Intent(CaptureVideoActivity.this,Insert_Artfrom_video_instructionActivity.class);
                startActivity(i);
            }



            @Override
            protected String doInBackground(Void... params) {
                Upload u = new Upload();
//                String filePath="";
//                try {
//                    filePath = SiliCompressor.with(getApplicationContext()).compressVideo(file.toString(), MediaStore.);
//                    Log.d("hmm filepath", filePath);
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }

//                String msg = u.upLoad2Server(file.toString(),dataToGet);
                String msg = u.upLoad2Server(file.toString(),dataToGet);
                return msg;
            }
        }
        UploadVideo uv = new UploadVideo();
        uv.execute();
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
//    }
}



//package com.example.artisanprofilingapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.content.FileProvider;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.text.Html;
//import android.text.method.LinkMovementMethod;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.PrivateKey;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class CaptureVideoActivity extends AppCompatActivity {
//    private Button capture;
//    private File file;
//    private Uri file_uri,uri;
//    private String videopath="";
//    static final int REQUEST_VIDEO_CAPTURE = 1;
//    private static final int PERMISSION_REQUEST_CODE = 100;
//    private String video_name;
//    RequestQueue requestQueue;
//    ProgressDialog progressDialog;
//    SharedPreferences myPref;
//    private String dataToGet;
//    private MediaPlayer mediaPlayer;
//    private VideoView displayRecordedVideo;
//    private String pathToStoredVideo;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_capture_video);
//        capture = findViewById(R.id.capture);
//        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
//        dataToGet = myPref.getString("id","No data found");
//        Log.d("hiii", "onCreate: "+dataToGet);
//        mediaPlayer = MediaPlayer.create(this, R.raw.profilevideocaptureinst);
//        mediaPlayer.start();
//        displayRecordedVideo = (VideoView)findViewById(R.id.videodisplay);
//        // Creating Volley newRequestQueue .
//        requestQueue = Volley.newRequestQueue(CaptureVideoActivity.this);
//        progressDialog = new ProgressDialog(CaptureVideoActivity.this);
//        capture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                captureVideo();
//            }
//        });
//    }
//    private void captureVideo(){
//        Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        if(i.resolveActivity(getPackageManager())!=null){
//            if (!checkPermission()) {
//                requestPermission(); // Code for permission
//            }
//            try {
//                getFileUri();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            uri = FileProvider.getUriForFile(CaptureVideoActivity.this,BuildConfig.APPLICATION_ID+".provider",file);
//            i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            startActivityForResult(i,REQUEST_VIDEO_CAPTURE);
////            makeRequest();
//        }
//    }
//
//    private void getFileUri() throws IOException {
//        //img_type = "yes";
//        video_name = dataToGet+"_intro";
//        //img_type = ".jpg";
////        File[] s= getExternalMediaDirs();
////        String str = "";
////        for(File f:s){
////            str+=String.valueOf(f);
////        }
//        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"Camera");
//        file = File.createTempFile(video_name,".mp4",dir);
//        Log.d("hmm" , String.valueOf(file));
////        file_uri = Uri.fromFile(file);
//        pathToStoredVideo = "file:"+file.getAbsolutePath();
////        Log.d("hmm" , String.valueOf(file_uri));
////        Log.d("hmm" , String.valueOf(file_uri.toString()));
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode==RESULT_OK){
////            uri = Uri.parse(intent.getStringExtra(MediaStore.EXTRA_OUTPUT));
////            Intent data = new Intent(Intent.ACTION_VIEW);
////        uri = FileProvider.getUriForFile(CaptureVideoActivity.this,this.getApplicationContext().getPackageName()+".provider",getOut) '
////            videopath = getRealPathFromURI(uri);
////           uri = Uri.parse(file.getAbsolutePath());
////            file = new File(getRealPathFromURI(uri));
//            uri = Uri.parse(pathToStoredVideo);
////            File fl = new File(Objects.requireNonNull(uri.getPath()));
////            try{
////                InputStream ims = new FileInputStream(fl);
////            }
////            Log.d("hmm videopath->", videopath);
//            displayRecordedVideo.setVideoURI(uri);
//
////            pathToStoredVideo = getRealPathFromURIPath(uri, CaptureVideoActivity.this);
////           displayRecordedVideo.start();
////
//            Log.d("eirki", String.valueOf(uri));
//            Toast.makeText(this,"Video Captured Properly",Toast.LENGTH_SHORT).show();
//            //makeRequest();
//            uploadVideo();
//
//
//        }
//    }
//    private String getFileDestinationPath(){
//        String generatedFilename = String.valueOf(System.currentTimeMillis());
//        String filePathEnvironment = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
//        File directoryFolder = new File(filePathEnvironment + "/video/");
//        if(!directoryFolder.exists()){
//            directoryFolder.mkdir();
//        }
//        Log.d("Hoye ja bhai", "Full path " + filePathEnvironment + "/video/" + generatedFilename + ".mp4");
//        return filePathEnvironment + "/video/" + generatedFilename + ".mp4";
//    }
//    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
//        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) {
//            return contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
//            return cursor.getString(idx);
//        }
//    }
//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(CaptureVideoActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (result == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private void requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Toast.makeText(this, "Write External Storage permission allows us to save files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("value", "Permission Granted, Now you can use local drive .");
//                } else {
//                    Log.e("value", "Permission Denied, You cannot use local drive .");
//                }
//                break;
//        }
//    }
//
////    public void makeRequest(){
////        String myurl = "http://192.168.43.12/Artisans-Profiling/videoupload.php";
////
////        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
////        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String ServerResponse) {
////                        // Hiding the progress dialog after all task complete.
////                        progressDialog.dismiss();
////                        // Showing response message coming from server.
////                        Toast.makeText(CaptureVideoActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
////                    }
////                },
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError volleyError) {
////                        // Hiding the progress dialog after all task complete.
////                        progressDialog.dismiss();
////                        // Showing error message if something goes wrong.
////                        Toast.makeText(CaptureVideoActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
////
////                    }
////                })
////        {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                HashMap<String,String> map = new HashMap<>();
////                map.put("file_path",String.valueOf(file_uri));
////                map.put("video_name",video_name);
////
////                return map;
////            }
////        };
////        queue.add(stringRequest);
//
//        private void uploadVideo() {
//            class UploadVideo extends AsyncTask<Void, Void, String> {
//
//                ProgressDialog uploading;
//
//                @Override
//                protected void onPreExecute() {
//                    super.onPreExecute();
//                    uploading = ProgressDialog.show(CaptureVideoActivity.this, "Uploading File", "Please wait...", false, false);
//                }
//
//                protected void onPostExecute(String s) {
//                    super.onPostExecute(s);
//                    uploading.dismiss();
//                    Toast.makeText(CaptureVideoActivity.this, s, Toast.LENGTH_LONG).show();
////                    textViewResponse.setText(Html.fromHtml("<b>Uploaded at <a href='" + s + "'>" + s + "</a></b>"));
////                    textViewResponse.setMovementMethod(LinkMovementMethod.getInstance());
////                    myPref.edit().putString("track", "10").apply();
//                    myPref.edit().putString("track", "14").apply();
//                    Intent i=new Intent(CaptureVideoActivity.this,Insert_Artfrom_video_instructionActivity.class);
//                    startActivity(i);
//                }
//
//                @Override
//                protected String doInBackground(Void... params) {
//                    Upload u = new Upload();
//                    Log.d("hmmm", "hmmm"+file.toString());
//                    File ff = new File(pathToStoredVideo);
//                    String msg = u.upLoad2Server(ff.toString(),dataToGet);
//                    return msg;
//                }
//            }
//            UploadVideo uv = new UploadVideo();
//            uv.execute();
//        }
//
//    public String getRealPathFromURI(Uri contentUri) {
//        String[] proj = { MediaStore.Video.Media.DATA };
//        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }
//
////    }
//}