package com.bianki.biankiapp.Bio;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.HomePage.Home;
import com.bianki.biankiapp.R;

import java.util.Locale;

public class Addbio extends AppCompatActivity implements HomeView {

    EditText bio ;
    Button next ;
    TextView skip ;
    ProgressBar progressBar ;
    AddBioPresenter addBioPresenter ;
    Helper helper;
    String cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbio);

        bio = findViewById(R.id.bio);
        next = findViewById(R.id.next);
        skip = findViewById(R.id.skip);
        progressBar = findViewById(R.id.progressbar);


        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        addBioPresenter = new AddBioPresenter(this);




        bio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (null != bio.getLayout() && bio.getLayout().getLineCount() > 5) {
                    bio.getText().delete(bio.getText().length() - 1, bio.getText().length());
                }
            }
        });


        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String localbio = bio.getText().toString();

                addBioPresenter.addBio(localbio, cursor);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();


            }
        });


        TextView detailsbio = findViewById(R.id.detailsbio);
        TextView addbio = findViewById(R.id.addbio);


        if (Locale.getDefault().getLanguage().equals("ar")) {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Tajawal-Medium.ttf");
            detailsbio.setTypeface(MLight);
            addbio.setTypeface(MLight);
            next.setTypeface(MLight);
            skip.setTypeface(MLight);
            bio.setTypeface(MLight);


        } else {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
            detailsbio.setTypeface(MLight);
            addbio.setTypeface(MLight);
            next.setTypeface(MLight);
            skip.setTypeface(MLight);
            bio.setTypeface(MLight);

        }
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void onErrorLoading(String message) {

        Toast.makeText(this, "عفوا حدث خطا ما", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void service(String result, Boolean message) {

        if (message)
        {
            startActivity(new Intent(getApplicationContext() , Home.class));
            finish();
        }
        else
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBackPressed() {


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }


}
