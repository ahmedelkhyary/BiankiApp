package com.bianki.biankiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bianki.biankiapp.CreateGroup.creategroup;

public class Groups extends AppCompatActivity {

    ImageView back ;
    LinearLayout create ;
    LinearLayout mygroup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        back = findViewById(R.id.back);
        create = findViewById(R.id.create);
        mygroup = findViewById(R.id.mygroup);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , creategroup.class));
            }
        });

        mygroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , com.bianki.biankiapp.MyGroup.mygroup.class));
            }
        });
    }
}
