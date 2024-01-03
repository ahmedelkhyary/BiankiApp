package com.bianki.biankiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Fullscreenimage extends AppCompatActivity {

    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreenimage);

        imageView = findViewById(R.id.image);
        Intent intent = getIntent();
        String path = intent.getStringExtra("photo");
        try {
            Picasso.get().load(path).into(imageView);



        } catch (Exception e) {
        }
    }
}
