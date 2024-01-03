package com.bianki.biankiapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bianki.biankiapp.AddphotoAndBio.AddPhotoAndBio;
import com.bianki.biankiapp.Bio.Addbio;

import java.util.Locale;

public class success extends AppCompatActivity {

    Button complete ;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);



        intent = getIntent();
        String facebookandgoogle = intent.getStringExtra("facebookandgoogle");
        complete = findViewById(R.id.complete);
        TextView textView = findViewById(R.id.textView);





        complete.setOnClickListener(new View.OnClickListener() {

            String name = intent.getStringExtra("key");

            @Override
            public void onClick(View view) {

                if(name.equals("sgin"))
                {
                    startActivity(new Intent(getApplicationContext() , AddPhotoAndBio.class ));
                    finish();
                }else
                {
                    startActivity(new Intent(getApplicationContext() , Addbio.class ));
                    finish();
                }


            }
        });



        if(Locale.getDefault().getLanguage().equals("ar"))
        {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Tajawal-Medium.ttf");
            complete.setTypeface(MLight);
            textView.setTypeface(MLight);



        }else
        {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
            complete.setTypeface(MLight);
            textView.setTypeface(MLight);
        }

    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
}
