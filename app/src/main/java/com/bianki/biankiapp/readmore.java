package com.bianki.biankiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class readmore extends AppCompatActivity {

    TextView content ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readmore);

        content = findViewById(R.id.content);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");

        content.setText(text);


    }
}
