package com.example.artisanprofilingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class EditInsert_image_instructionActivity extends AppCompatActivity {
    Button submitbtn;
    private static final int PERMISSION_REQUEST_CODE = 100;
    SharedPreferences myPref;
    private MediaPlayer sareemediaPlayer, kurtamediaPlayer, tshirtmediaPlayer, bagmediaPlayer, showpiecemediaPlayer, goinamediaPlayer;
    private ImageView img1,img2,img3,img4;
    private TextView inst;
    private String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_insert_image_instruction);

        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        inst = findViewById(R.id.picInstruction);

        sareemediaPlayer = MediaPlayer.create(this, R.raw.sareeinst);
        bagmediaPlayer = MediaPlayer.create(this, R.raw.baginst);
        tshirtmediaPlayer = MediaPlayer.create(this, R.raw.tshirtinst);
        goinamediaPlayer = MediaPlayer.create(this, R.raw.goinainst);
        kurtamediaPlayer = MediaPlayer.create(this, R.raw.kurtainst);
        showpiecemediaPlayer = MediaPlayer.create(this, R.raw.showpieceinst);

submitbtn = findViewById(R.id.submitBtn);
        selected = myPref.getString("selected","none");
        try {
            switch (selected) {
                case "saree":
                    inst.setText("শাড়ীর ছবি তোলার নির্দেশাবলী:-\n"+
                            "\n" +
                            "- শাড়ীর প্রতি টা অংশ ছবি তে পরিষ্কার ভাবে দেখা যাবে\n" +
                            "- পুরো শাড়ী টা নিয়ে একটা ছবি তুলুন\n" +
                            "- শাড়ীর ওপরে করা ডিজাইন টার একটা সামনে থেকে ছবি তুলুন \n" +
                            "- ছবি গুলো সোজা করে পরিষ্কার করে তুলবেন\n" +
                            "- ছবি গুলো ঠিকঠাক আলোতে রেখে তুলবেন\n" +
                            "- ছবি গুলো তোলার সময়ে ব্যাকগ্রাউন্ড টা হালকা রং এর থাকবে\n"+
                            "\n" +
                            "শাড়ীর কয়েকটা স্যাম্পল ছবি:-");
                    img1.setImageResource(R.drawable.saree1);
                    img2.setImageResource(R.drawable.saree2);
                    img3.setImageResource(R.drawable.saree3);
                    img4.setVisibility(View.GONE);

                    sareemediaPlayer.start();
                    break;
                case "kurta":
                    inst.setText("কুর্তার ছবি তোলার নির্দেশাবলী:-  \n" +
                            "\n" +
                            "- পুরো কুর্তা টার ছবি তুলুন, সামনে এবং পিছন থেকে \n" +
                            "- কুর্তার ওপর করা ডিজাইন টার একটা সামনে থেকে ছবি তুলুন\n" +
                            "- কুর্তার গলা টার একটা সামনে থেকে ছবি তুলুন\n" +
                            "- কুর্তার হাতা দুটোর সামনে থেকে ছবি তুলুন\n" +
                            "- ছবি গুলো সোজা করে পরিষ্কার করে তুলবেন\n" +
                            "- ছবি গুলো ঠিকঠাক আলোতে রেখে তুলবেন\n" +
                            "- ছবি গুলো তোলার সময়ে ব্যাকগ্রাউন্ড টা হালকা রং এর থাকবে\n" +
                            "\n" +
                            "কুর্তার কয়েকটা স্যাম্পল ছবি:-\n");
                    img1.setImageResource(R.drawable.kurta1);
                    img2.setImageResource(R.drawable.kurta2);
                    img3.setImageResource(R.drawable.kurta3);
                    img4.setImageResource(R.drawable.kurta4);
//                    kurtamediaPlayer.prepare();
                    kurtamediaPlayer.start();
                    break;
                case "tshirt":
                    inst.setText("টি-শার্টের ছবি তোলার নির্দেশাবলী:-\n" +
                            "\n" +
                            "- পুরো টি-শার্ট টার ছবি তুলুন, সামনে এবং পিছন থেকে\n" +
                            "- টি-শার্ট এর ওপরে করা ডিজাইন টার সামনে থেকে ছবি তুলুন\n" +
                            "- ছবি গুলো সোজা করে পরিষ্কার করে তুলবেন\n" +
                            "- ছবি গুলো ঠিকঠাক আলোতে রেখে তুলবেন\n" +
                            "- ছবি গুলো তোলার সময়ে ব্যাকগ্রাউন্ড টা হালকা রং এর থাকবে\n" +
                            "\n" +
                            "টি-শার্টের কয়েকটা স্যাম্পল ছবি:-\n");
                    img1.setImageResource(R.drawable.tshirt1);
                    img2.setImageResource(R.drawable.tshirt2);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
//                    tshirtmediaPlayer.prepare();
                    tshirtmediaPlayer.start();
                    break;
                case "showpiece":
                    inst.setText("শো পিস্ এর ছবি তোলার নির্দেশাবলী:-\n" +
                            "\n" +
                            "- পুরো জিনিস টার  ছবি তুলবেন\n" +
                            "- ছবি গুলো সোজা করে পরিষ্কার করে তুলবেন\n" +
                            "- ছবি গুলো ঠিকঠাক আলোতে রেখে তুলবেন\n" +
                            "- ছবি গুলো তোলার সময়ে ব্যাকগ্রাউন্ড টা হালকা রং এর থাকবে\n" +
                            "\n" +
                            "- কিছু স্যাম্পল ছবি:-\n");
                    img1.setImageResource(R.drawable.showpiece1);
                    img2.setImageResource(R.drawable.showpiece2);
                    img3.setImageResource(R.drawable.showpiece3);
                    img4.setImageResource(R.drawable.showpiece4);
//                    showpiecemediaPlayer.prepare();
                    showpiecemediaPlayer.start();
                    break;
                case "bag":
                    inst.setText("ব্যাগের ছবি তোলার নির্দেশাবলী:-\n" +
                            "\n" +
                            "- ব্যাগের সামনে এবং পিছন থেকে ছবি তুলুন\n" +
                            "- ব্যাগের সাইড থেকে একটা ছবি তুলুন'\n" +
                            "- ব্যাগের ওপর করা ডিজাইন এর সামনে থেকে ছবি তুলুন\n" +
                            "- ব্যাগ এর হ্যান্ডেল এর ছবি তুলুন\n" +
                            "- ব্যাগ টা খুলে, ব্যাগের ভিতরের ছবি তুলুন\n" +
                            "- ছবি গুলো সোজা করে পরিষ্কার করে তুলবেন\n" +
                            "- ছবি গুলো ঠিকঠাক আলোতে রেখে তুলবেন\n" +
                            "- ছবি গুলো তোলার সময়ে ব্যাকগ্রাউন্ড টা হালকা রং এর থাকবে\n" +
                            "\n" +
                            "ব্যাগের কিছু স্যাম্পল ছবি:-  \n");
                    img1.setImageResource(R.drawable.bag1);
                    img2.setImageResource(R.drawable.bag2);
                    img3.setImageResource(R.drawable.bag3);
                    img4.setVisibility(View.GONE);
//                    bagmediaPlayer.prepare();
                    bagmediaPlayer.start();
                    break;
                case "goina":
                    inst.setText("গয়নার ছবি তোলার নির্দেশাবলী:-\n" +
                            "\n" +
                            "- পুরো গয়না টার ছবি তুলুন\n" +
                            "- ছবি গুলো সোজা করে পরিষ্কার করে তুলবেন\n" +
                            "- ছবি গুলো ঠিকঠাক আলোতে রেখে তুলবেন\n" +
                            "- ছবি গুলো তোলার সময়ে ব্যাকগ্রাউন্ড টা হালকা রং এর থাকবে\n" +
                            "\n" +
                            "গয়নার কিছু স্যাম্পল ছবি:-\n");
                    img1.setImageResource(R.drawable.goina1);
                    img2.setImageResource(R.drawable.goina2);
                    img3.setImageResource(R.drawable.goina3);
                    img4.setVisibility(View.GONE);
//                    goinamediaPlayer.prepare();
                    goinamediaPlayer.start();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Some Error Occured!", Toast.LENGTH_LONG).show();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermission()) {

                    requestPermission();
                }
                else{
                    //mediaPlayer.start();
                }
            }
        }

//        String SelectedType = myPref.getString("selected","No data found");
//        if(SelectedType=="saree"){
//
//        }


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
//                    myPref.edit().putString("track", "10").apply();
                    sareemediaPlayer.stop();
                    tshirtmediaPlayer.stop();
                    bagmediaPlayer.stop();
                    goinamediaPlayer.stop();
                    showpiecemediaPlayer.stop();
                    kurtamediaPlayer.stop();

                    Intent i = new Intent(EditInsert_image_instructionActivity.this, EditCaptureImageActivity.class);
                    startActivity(i);


                }
                else{
                    Intent intent = new Intent(EditInsert_image_instructionActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }



        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(EditInsert_image_instructionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //int result2 = ContextCompat.checkSelfPermission(Insert_image_instructionActivity.this, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed(){
        sareemediaPlayer.stop();
        tshirtmediaPlayer.stop();
        bagmediaPlayer.stop();
        goinamediaPlayer.stop();
        showpiecemediaPlayer.stop();
        kurtamediaPlayer.stop();
        super.onBackPressed();
    }
//    private void requestPermission() {
//        int i=0;
//        if (ActivityCompat.shouldShowRequestPermissionRationale(Insert_image_instructionActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            i=1;
//            Toast.makeText(Insert_image_instructionActivity.this, "Write External Storage permission allows us to save files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        }
////        if(ActivityCompat.shouldShowRequestPermissionRationale(Insert_image_instructionActivity.this, android.Manifest.permission.CAMERA)) {
////            i=2;
////            Toast.makeText(Insert_image_instructionActivity.this, "Camera permission allows us to Click Pictures. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
////        }
//
//
//        else {
//            i=3;
//            ActivityCompat.requestPermissions(Insert_image_instructionActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
////            ActivityCompat.requestPermissions(Insert_image_instructionActivity.this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
//        }
//        Log.d("check",Integer.toString(i));
////        Intent ii = new Intent(Insert_image_instructionActivity.this, CaptureImageActivity.class);
////        startActivity(ii);
//    }

    private void requestPermission() {
        int i=0;
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            i=1;
//            Toast.makeText(this, "Camera permission allows us to click images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        }
//
//
//
//        else {
//            i=3;
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//
//        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is needed to access files from your device...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(EditInsert_image_instructionActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},666);
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
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},666);
        }
        Log.d("check",Integer.toString(i));
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    myPref.edit().putString("track", "6").apply();
//                    Log.e("value", "Permission Granted, Now you can use local drive .");
//                    Intent i = new Intent(Insert_image_instructionActivity.this, CaptureImageActivity.class);
//                    startActivity(i);
//                } else {
//                    Log.e("value", "Permission Denied, You cannot use local drive .");
//                }
//                break;
////            case 9:
////                if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
////                    Log.e("value", "Permission Granted, Now you can use camera .");
////                } else {
////                    Log.e("value", "Permission Denied, You cannot use camera.");
////                }
////                break;
//        }
        switch (requestCode) {

            case 666: // Allowed was selected so Permission granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Log.d("Jhingalala", "granted");
                    //mediaPlayer.start();

                    // do your work here

                } else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {
                    // User selected the Never Ask Again Option Change settings in app settings manually
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setTitle("Change Permissions in Settings");
                    alertDialogBuilder
                            .setMessage("" +
                                    "\nClick SETTINGS to Manually Set\n" + "Permissions to use Database Storage")
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setTitle("Second Chance");
                        alertDialogBuilder
                                .setMessage("Click RETRY to Set Permissions to Allow\n\n" + "Click EXIT to the Close App")
                                .setCancelable(false)
                                .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Integer.parseInt(WRITE_EXTERNAL_STORAGE));
                                        Intent i = new Intent(EditInsert_image_instructionActivity.this, EditInsert_image_instructionActivity.class);
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