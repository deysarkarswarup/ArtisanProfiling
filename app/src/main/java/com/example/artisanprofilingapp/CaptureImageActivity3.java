package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class CaptureImageActivity3 extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private Button button;
    private String encoded_string, image_name;
    int count = 0;
    private Bitmap bitmap;
    private File file;
    private Uri file_uri;
    SharedPreferences myPref;
    private String dataToGet, idToGet;
    private String ImageCountToGet;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image3);

        button = (Button) findViewById(R.id.start);
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        dataToGet = myPref.getString("phone","No data found");
        idToGet = myPref.getString("id","No Data found");
        ImageCountToGet = myPref.getString("count","No data found");
        mediaPlayer = MediaPlayer.create(this, R.raw.captureimage3);

        mediaPlayer.start();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            if (checkPermission()) {
                                getFileUri();
                            } else {
                                requestPermission(); // Code for permission
                                //getFileUri();
                            }
                        }
                    } else {
                        getFileUri();
                    }
                }
                else{
                    Intent intent = new Intent(CaptureImageActivity3.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
                count = Integer.parseInt(ImageCountToGet) +1;
                Log.d("count",String.valueOf(count));
                ImageCountToGet = String.valueOf(count);
                myPref.edit().putString("count", ImageCountToGet).apply();
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                startActivityForResult(i, 10);
            }
        });
    }

    private void getFileUri() {
        //img_type = "yes";
        image_name = "_3_"+ dataToGet + ".jpg";
        //img_type = ".jpg";
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + count + image_name
        );
        Log.d("msg" , String.valueOf(file));
        file_uri = Uri.fromFile(file);
        Log.d("msg" , String.valueOf(file_uri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            new CaptureImageActivity3.Encode_image().execute();
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
            Toast.makeText(CaptureImageActivity3.this, "picture submitted successfully!", Toast.LENGTH_LONG).show();

            myPref.edit().putString("track", "13").apply();
            mediaPlayer.stop();
            Intent i=new Intent(CaptureImageActivity3.this,CaptureImageActivity4.class);
            startActivity(i);
        }
    }

    private void makeRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

//        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.12/Artisans-Profiling/imageupload.php",

        StringRequest request = new StringRequest(Request.Method.POST, "https://artisanapp.xyz/imageupload.php",

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
                Log.d("eirki id-->", idToGet);
                map.put("id",idToGet);


                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private boolean checkPermission() {
        // int result = ContextCompat.checkSelfPermission(CaptureImageActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(CaptureImageActivity3.this, Manifest.permission.CAMERA);
        if (/*result == PackageManager.PERMISSION_GRANTED && */result2 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        int i=0;
//        if (ActivityCompat.shouldShowRequestPermissionRationale(CaptureImageActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//           i=1;
//            Toast.makeText(CaptureImageActivity.this, "Write External Storage permission allows us to save files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(CaptureImageActivity3.this, android.Manifest.permission.CAMERA)) {
            i=2;
            Toast.makeText(CaptureImageActivity3.this, "Camera permission allows us to Click Pictures. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        }


        else {
            i=3;
            //ActivityCompat.requestPermissions(CaptureImageActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            ActivityCompat.requestPermissions(CaptureImageActivity3.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        }
        Log.d("check",Integer.toString(i));
    }

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