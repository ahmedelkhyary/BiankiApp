package com.bianki.biankiapp.Update;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;

public class updatereplay extends AppCompatActivity implements HomeView {

    Updateresenter updateresenter ;
    Helper helper ;
    String cursor ;
    EditText editText ;
    Button updataoftext ;
    String id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatereplay);

        updateresenter = new Updateresenter(this);
        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        editText = findViewById(R.id.text);
        updataoftext = findViewById(R.id.update);

        Intent intent =  getIntent();
        String text = intent.getStringExtra("text");
         id = intent.getStringExtra("id");

        editText.setText(text);


        updataoftext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateresenter.EditAskReplay(editText.getText().toString() , id , cursor );
            }
        });


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void Result(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (message.equals("replay updated Successfully"))
        {
            finish();
        }

    }
}