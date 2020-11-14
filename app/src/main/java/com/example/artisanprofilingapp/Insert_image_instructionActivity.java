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

public class Insert_image_instructionActivity extends AppCompatActivity {
    Button submitbtn;
    private static final int PERMISSION_REQUEST_CODE = 100;
    SharedPreferences myPref;
    private MediaPlayer sareemediaPlayer, kurtamediaPlayer, tshirtmediaPlayer, bagmediaPlayer,
            wrapperskirtmediaPlayer, goinamediaPlayer, palazzomediaPlayer, cushioncovermediaPlayer,
            leatherbagmediaPlayer, blousepiecemediaPlayer, homedecormediaPlayer, utilitymediaPlayer,
            paintingmediaPlayer, stolesmediaPlayer, handkerchiefmediaPlayer;
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8;
    private TextView inst;
    private String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_image_instruction);
        submitbtn = (Button)findViewById(R.id.submitBtn);
        myPref = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        inst = findViewById(R.id.picInstruction);

//        sareemediaPlayer = MediaPlayer.create(this, R.raw.sareeinst);
//        bagmediaPlayer = MediaPlayer.create(this, R.raw.baginst);
//        tshirtmediaPlayer = MediaPlayer.create(this, R.raw.tshirtinst);
//        goinamediaPlayer = MediaPlayer.create(this, R.raw.goinainst);
//        kurtamediaPlayer = MediaPlayer.create(this, R.raw.kurtainst);
//        showpiecemediaPlayer = MediaPlayer.create(this, R.raw.showpieceinst);


        selected = myPref.getString("selected","none");
        try {
            switch (selected) {
                case "saree":
                    inst.setText("শাড়ির ছবি তোলার জন্য কিছু প্রাথমিক পরামর্শ\n" +
                            "•\tপুরো শাড়িটা নিয়ে একটা ছবি তুলুন \n" +
                            "•\tশাড়ির উপরে ডিজাইনের একটা ছবি তুলুন \n" +
                            "•\tশাড়ির আঁচলের ডিজাইনের সামনে থেকে ছবি তুলুন\n" +
                            "•\tশাড়ির ব্লাউজ পিস থাকলে তার একটা ছবি দিন\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলবেন \n" +
                            "•\tছবি গুলো ঠিকঠাক আলোতে রেখে তুলবেন \n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড টা হালকা রঙের থাকবে \n" +
                            "•\tছবি তোলার সময় আপনার মোবাইল ফোনটি আড়াআড়ি ভাবে ধরবেন \n" +
                            "\n" +
                            "নিচে  শাড়ির কয়েকটা স্যাম্পল ছবি দেখুন \n");
                    img1.setImageResource(R.drawable.saree1);
                    img2.setImageResource(R.drawable.saree2);
                    img3.setImageResource(R.drawable.saree3);
                    img4.setImageResource(R.drawable.saree4);
                    img5.setImageResource(R.drawable.saree5);
                    img6.setImageResource(R.drawable.saree6);
                    img7.setImageResource(R.drawable.saree7);
                    img8.setImageResource(R.drawable.saree8);
                    sareemediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    sareemediaPlayer.start();
                    break;
                case "goina":
                    inst.setText("গয়নার ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো গয়নার সেটের একটা ছবি তুলুন।\n" +
                            "•\tগয়নার টির বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tগয়নার ওপর ডিজাইনের একটা ছবি কাছ থেকে তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tসবুজ পাতা বা ফুল ইত্যাদি সামগ্রী ব্যাবহার করা যেতে পারে।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে গয়নার কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.jewellery1);
                    img2.setImageResource(R.drawable.jewellery2);
                    img3.setImageResource(R.drawable.jewellery3);
                    img4.setImageResource(R.drawable.jewellery4);
                    img5.setImageResource(R.drawable.jewellery5);
                    img6.setImageResource(R.drawable.jewellery6);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    goinamediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    goinamediaPlayer.start();
                    break;
                case "bag":
                    inst.setText("ব্যাগ এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো ব্যাগটির একটি ছবি তুলুন।\n" +
                            "•\tব্যাগটির বিভিন্ন দিক থেকে ছবি তুলুন। \n" +
                            "•\tব্যাগ এর ওপর নকশার কাছ থেকে ছবি তুলুন। \n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tসবুজ পাতা বা ফুল ইত্যাদি সামগ্রী ব্যাবহার করা যেতে পারে।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "\n" +
                            "নিচে ব্যাগ এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.bag1);
                    img2.setImageResource(R.drawable.bag2);
                    img3.setImageResource(R.drawable.bag3);
                    img4.setImageResource(R.drawable.bag4);
                    img5.setImageResource(R.drawable.bag5);
                    img6.setImageResource(R.drawable.bag6);
                    img7.setImageResource(R.drawable.bag7);
                    img8.setImageResource(R.drawable.bag8);
                    bagmediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    bagmediaPlayer.start();
                    break;

                case "tshirt":
                    inst.setText("টটি-শার্ট এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো টি-শার্ট টির ছবি তুলুন।\n" +
                            "•\tটি-শার্ট টির বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tটি-শার্ট এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন। \n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে টি-শার্ট এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.tshirt1);
                    img2.setImageResource(R.drawable.tshirt2);
                    img3.setImageResource(R.drawable.tshirt3);
                    img4.setImageResource(R.drawable.tshirt4);
                    img5.setImageResource(R.drawable.tshirt5);
                    img6.setImageResource(R.drawable.tshirt6);
                    img7.setImageResource(R.drawable.tshirt7);
                    img8.setImageResource(R.drawable.tshirt8);
                    tshirtmediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    tshirtmediaPlayer.start();
                    break;
                case "wrapperskirt":
                    inst.setText("স্কার্ট এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো স্কার্টটির ছবি তুলুন।\n" +
                            "•\tস্কার্টটির বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tস্কার্ট এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে স্কার্ট এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.kurta1);
                    img2.setImageResource(R.drawable.kurta2);
                    img3.setImageResource(R.drawable.kurta3);
                    img4.setImageResource(R.drawable.kurta4);
                    img5.setImageResource(R.drawable.kurta5);
                    img6.setImageResource(R.drawable.kurta6);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    wrapperskirtmediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    wrapperskirtmediaPlayer.start();
                    break;
                case "palazzo":
                    inst.setText("পালাজো এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো পালাজো টির ছবি তুলুন।\n" +
                            "•\tপালাজো টির বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tপালাজো এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে পালাজো এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.palazzo1);
                    img2.setImageResource(R.drawable.palazzo2);
                    img3.setImageResource(R.drawable.palazzo3);
                    img4.setImageResource(R.drawable.palazzo4);
                    img5.setImageResource(R.drawable.palazzo5);
                    img6.setImageResource(R.drawable.palazzo6);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    palazzomediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    palazzomediaPlayer.start();
                    break;

                case "cushioncover":
                    inst.setText("কুশন কভার এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো কুশন কভার টির ছবি তুলুন।\n" +
                            "•\tকুশন কভার বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tকুশন কভার এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে কুশন কভার এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.cushion2);
                    img2.setImageResource(R.drawable.cushion3);
                    img3.setImageResource(R.drawable.cushion4);
                    img4.setImageResource(R.drawable.cushion5);
                    img5.setImageResource(R.drawable.cushion6);
                    img6.setImageResource(R.drawable.cushion7);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    cushioncovermediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    cushioncovermediaPlayer.start();
                    break;

                case "leatherbag":
                    inst.setText("চামড়ার ব্যাগ এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো ব্যাগটির একটি ছবি তুলুন।\n" +
                            "•\tব্যাগটির বিভিন্ন দিক থেকে ছবি তুলুন। \n" +
                            "•\tব্যাগ এর ওপর নকশার কাছ থেকে ছবি তুলুন। \n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tসবুজ পাতা বা ফুল ইত্যাদি সামগ্রী ব্যাবহার করা যেতে পারে।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "\n" +
                            "নিচে ব্যাগ এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.leatherbag1);
                    img2.setImageResource(R.drawable.leatherbag2);
                    img3.setImageResource(R.drawable.leatherbag3);
                    img4.setImageResource(R.drawable.leatherbag4);
                    img5.setImageResource(R.drawable.leatherbag5);
                    img6.setImageResource(R.drawable.leatherbag6);
                    img7.setImageResource(R.drawable.leatherbag7);
                    img8.setImageResource(R.drawable.leatherbag8);
                    leatherbagmediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    leatherbagmediaPlayer.start();
                    break;

                case "blousepiece":
                    inst.setText("ব্লাউস পিস এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো ব্লাউস পিস টির ছবি তুলুন।\n" +
                            "•\tব্লাউস পিস বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tব্লাউস পিস এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে ব্লাউস পিস এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.blouse1);
                    img2.setImageResource(R.drawable.blouse2);
                    img3.setImageResource(R.drawable.blouse3);
                    img4.setImageResource(R.drawable.blouse4);
                    img5.setImageResource(R.drawable.blouse5);
                    img6.setImageResource(R.drawable.blouse6);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    blousepiecemediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    blousepiecemediaPlayer.start();
                    break;

                case "kurta":
                    inst.setText("কুর্তা/কুর্তি এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো কুর্তা/কুর্তি টির ছবি তুলুন।\n" +
                            "•\tকুর্তা/কুর্তি বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tকুর্তা/কুর্তি এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে কুর্তা/কুর্তি এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.kurta1);
                    img2.setImageResource(R.drawable.kurta2);
                    img3.setImageResource(R.drawable.kurta3);
                    img4.setImageResource(R.drawable.kurta4);
                    img5.setImageResource(R.drawable.kurta5);
                    img6.setImageResource(R.drawable.kurta6);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    kurtamediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    kurtamediaPlayer.start();
                    break;

                case "homedecor":
                    inst.setText("শো-পিস এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো শো-পিস টির ছবি তুলুন।\n" +
                            "•\tশো-পিস বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tশো-পিস এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে শো-পিস এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.showpiece1);
                    img2.setImageResource(R.drawable.showpiece2);
                    img3.setImageResource(R.drawable.showpiece3);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    homedecormediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    homedecormediaPlayer.start();
                    break;

                case "utility":
                    inst.setText("অফিস স্টেশনারি এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো অফিস স্টেশনারি টির ছবি তুলুন।\n" +
                            "•\tঅফিস স্টেশনারি বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tঅফিস স্টেশনারি এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে অফিস স্টেশনারি এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.utility1);
                    img2.setImageResource(R.drawable.utility2);
                    img3.setImageResource(R.drawable.utility3);
                    img4.setImageResource(R.drawable.utility4);
                    img5.setImageResource(R.drawable.utility5);
                    img6.setImageResource(R.drawable.utility6);
                    img7.setImageResource(R.drawable.utility7);
                    img8.setImageResource(R.drawable.utility8);
                    utilitymediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    utilitymediaPlayer.start();
                    break;

                case "painting":
                    inst.setText("পেইন্টিং এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো পেইন্টিং টির ছবি তুলুন।\n" +
                            "•\tপেইন্টিং বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tপেইন্টিং এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে পেইন্টিং এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.painting1);
                    img2.setImageResource(R.drawable.painting2);
                    img3.setImageResource(R.drawable.painting3);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    paintingmediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    paintingmediaPlayer.start();
                    break;

                case "stoles":
                    inst.setText("স্টোল এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো স্টোল টির ছবি তুলুন।\n" +
                            "•\tস্টোল বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tস্টোল এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে স্টোল এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.stole1);
                    img2.setImageResource(R.drawable.stole2);
                    img3.setImageResource(R.drawable.stole3);
                    img4.setImageResource(R.drawable.stole4);
                    img5.setImageResource(R.drawable.stole5);
                    img6.setImageResource(R.drawable.stole6);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    stolesmediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    stolesmediaPlayer.start();
                    break;

                case "handkerchief":
                    inst.setText("রুমাল এর ছবি তোলার কিছু প্রাথমিক ধারণা\n" +
                            "•\tপুরো রুমাল টির ছবি তুলুন।\n" +
                            "•\tরুমাল বিভিন্ন দিক থেকে ছবি তুলুন।\n" +
                            "•\tরুমাল এর ওপর নকশার বা আঁকা ছবির কাছ থেকে ছবি তুলুন।\n" +
                            "•\tছবিগুলা ঠিকঠাক আলোতে রেখে তুলবেন।\n" +
                            "•\tছবিগুলো তোলার সময় ব্যাকগ্রাউন্ড  টা হালকা রঙের থাকবে।\n" +
                            "•\tছবিগুলো সোজা এবং পরিষ্কার করে তুলুন।\n" +
                            "•\tছবি তোলার সময় ফোন টি আড়াআড়ি ভাবে ধরুন।\n" +
                            "নিচে রুমাল এর কয়েকটি স্যাম্পল ছবি লক্ষ্য করুন\n");
                    img1.setImageResource(R.drawable.hankerchiep1);
                    img2.setImageResource(R.drawable.hankerchiep2);
                    img3.setImageResource(R.drawable.hankerchiep3);
                    img4.setImageResource(R.drawable.hankerchiep4);
                    img5.setImageResource(R.drawable.hankerchiep5);
                    img6.setImageResource(R.drawable.hankerchiep6);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    handkerchiefmediaPlayer = MediaPlayer.create(this, R.raw.slide81);
                    handkerchiefmediaPlayer.start();
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


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = con.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    myPref.edit().putString("track", "10").apply();
                    if(sareemediaPlayer.isPlaying()) {
                        sareemediaPlayer.stop();
                    }
                    else if(tshirtmediaPlayer.isPlaying()) {
                        tshirtmediaPlayer.stop();
                    }
                    else if(bagmediaPlayer.isPlaying()) {
                        bagmediaPlayer.stop();
                    }
                    else if(goinamediaPlayer.isPlaying()) {
                        goinamediaPlayer.stop();
                    }
                    else if(wrapperskirtmediaPlayer.isPlaying()) {
                        wrapperskirtmediaPlayer.stop();
                    }
                    else if(kurtamediaPlayer.isPlaying()) {
                        kurtamediaPlayer.stop();
                    }
                    else if(palazzomediaPlayer.isPlaying()) {
                        palazzomediaPlayer.stop();
                    }
                    else if(cushioncovermediaPlayer.isPlaying()) {
                        cushioncovermediaPlayer.stop();
                    }
                    else if(leatherbagmediaPlayer.isPlaying()) {
                        leatherbagmediaPlayer.stop();
                    }
                    else if(blousepiecemediaPlayer.isPlaying()) {
                        blousepiecemediaPlayer.stop();
                    }
                    else if(homedecormediaPlayer.isPlaying()) {
                        homedecormediaPlayer.stop();
                    }
                    else if(utilitymediaPlayer.isPlaying()) {
                        utilitymediaPlayer.stop();
                    }else if(paintingmediaPlayer.isPlaying()) {
                        paintingmediaPlayer.stop();
                    }
                    else if(stolesmediaPlayer.isPlaying()) {
                        stolesmediaPlayer.stop();
                    }else if(handkerchiefmediaPlayer.isPlaying()) {
                        handkerchiefmediaPlayer.stop();
                    }


                    Intent i = new Intent(Insert_image_instructionActivity.this, CaptureImageActivity.class);
                    startActivity(i);

                }
                else{
                    if(sareemediaPlayer.isPlaying()) {
                        sareemediaPlayer.stop();
                    }
                    else if(tshirtmediaPlayer.isPlaying()) {
                        tshirtmediaPlayer.stop();
                    }
                    else if(bagmediaPlayer.isPlaying()) {
                        bagmediaPlayer.stop();
                    }
                    else if(goinamediaPlayer.isPlaying()) {
                        goinamediaPlayer.stop();
                    }
                    else if(wrapperskirtmediaPlayer.isPlaying()) {
                        wrapperskirtmediaPlayer.stop();
                    }
                    else if(kurtamediaPlayer.isPlaying()) {
                        kurtamediaPlayer.stop();
                    }
                    else if(palazzomediaPlayer.isPlaying()) {
                        palazzomediaPlayer.stop();
                    }
                    else if(cushioncovermediaPlayer.isPlaying()) {
                        cushioncovermediaPlayer.stop();
                    }
                    else if(leatherbagmediaPlayer.isPlaying()) {
                        leatherbagmediaPlayer.stop();
                    }
                    else if(blousepiecemediaPlayer.isPlaying()) {
                        blousepiecemediaPlayer.stop();
                    }
                    else if(homedecormediaPlayer.isPlaying()) {
                        homedecormediaPlayer.stop();
                    }
                    else if(utilitymediaPlayer.isPlaying()) {
                        utilitymediaPlayer.stop();
                    }else if(paintingmediaPlayer.isPlaying()) {
                        paintingmediaPlayer.stop();
                    }
                    else if(stolesmediaPlayer.isPlaying()) {
                        stolesmediaPlayer.stop();
                    }else if(handkerchiefmediaPlayer.isPlaying()) {
                        handkerchiefmediaPlayer.stop();
                    }
                    Intent intent = new Intent(Insert_image_instructionActivity.this, InternetCheckActivity.class);
                    startActivity(intent);
                    finish();
                }
            }



        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Insert_image_instructionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed(){
        if(sareemediaPlayer.isPlaying()) {
            sareemediaPlayer.stop();
        }
        else if(tshirtmediaPlayer.isPlaying()) {
            tshirtmediaPlayer.stop();
        }
        else if(bagmediaPlayer.isPlaying()) {
            bagmediaPlayer.stop();
        }
        else if(goinamediaPlayer.isPlaying()) {
            goinamediaPlayer.stop();
        }
        else if(wrapperskirtmediaPlayer.isPlaying()) {
            wrapperskirtmediaPlayer.stop();
        }
        else if(kurtamediaPlayer.isPlaying()) {
            kurtamediaPlayer.stop();
        }
        else if(palazzomediaPlayer.isPlaying()) {
            palazzomediaPlayer.stop();
        }
        else if(cushioncovermediaPlayer.isPlaying()) {
            cushioncovermediaPlayer.stop();
        }
        else if(leatherbagmediaPlayer.isPlaying()) {
            leatherbagmediaPlayer.stop();
        }
        else if(blousepiecemediaPlayer.isPlaying()) {
            blousepiecemediaPlayer.stop();
        }
        else if(homedecormediaPlayer.isPlaying()) {
            homedecormediaPlayer.stop();
        }
        else if(utilitymediaPlayer.isPlaying()) {
            utilitymediaPlayer.stop();
        }else if(paintingmediaPlayer.isPlaying()) {
            paintingmediaPlayer.stop();
        }
        else if(stolesmediaPlayer.isPlaying()) {
            stolesmediaPlayer.stop();
        }else if(handkerchiefmediaPlayer.isPlaying()) {
            handkerchiefmediaPlayer.stop();
        }
        super.onBackPressed();
    }

    private void requestPermission() {
        int i=0;
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is needed to access files from your device...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Insert_image_instructionActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},666);
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
        switch (requestCode) {

            case 666: // Allowed was selected so Permission granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

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
                                        Intent i = new Intent(Insert_image_instructionActivity.this, Insert_image_instructionActivity.class);
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