package com.bianki.biankiapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bianki.biankiapp.Login.login;

public class Splach extends AppCompatActivity {

    ImageView imageViewSplash ;
    Animation animation ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);


        imageViewSplash = findViewById(R.id.imageViewSplash);
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageViewSplash.startAnimation(animation);


        int SPLASH_DISPLAY_LENGTH = 2000;
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(Splach.this, login.class);
            startActivity(mainIntent);
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

