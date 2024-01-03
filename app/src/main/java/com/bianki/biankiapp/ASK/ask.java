package com.bianki.biankiapp.ASK;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterQuestion;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterQuestionPrivate;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.puplicQuestion;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;

import java.util.List;

public class ask extends AppCompatActivity implements HomeView {

    ImageView fab ;
    LinearLayout backgroundtext ;
    EditText text ;
    RecyclerView recyclerView ;
    Button post ;
    Helper helper ;
    String cursor ;
    ASKPresenter askPresenter ;
    SwitchCompat switchbutton ;
    String type = "exist" ;
    LottieAnimationView progressbar ;
    TextView publictext ;
    TextView privatetext ;
    boolean flag = true ;
    RecyclerViewHomeAdapterQuestion headerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        fab = findViewById(R.id.fab);
        backgroundtext = findViewById(R.id.backgroundtext);
        text = findViewById(R.id.gettext);
        switchbutton = findViewById(R.id.switchbutton);

        recyclerView = findViewById(R.id.recyclerView);
        post = findViewById(R.id.post);
        progressbar = findViewById(R.id.progressbar);
        publictext = findViewById(R.id.publictext);
        privatetext = findViewById(R.id.privatetext);

        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        askPresenter = new ASKPresenter(this);
        askPresenter.puplicQuestion(cursor);


        privatetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false ;
                publictext.setTextColor(getResources().getColor(R.color.blacklow));
                privatetext.setTextColor(getResources().getColor(R.color.black));
                askPresenter.privateQuestion(cursor);

            }
        });

        publictext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true ;
                publictext.setTextColor(getResources().getColor(R.color.black));
                privatetext.setTextColor(getResources().getColor(R.color.blacklow));
                askPresenter.puplicQuestion(cursor);


            }
        });




        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                askPresenter.puplicAsk(text.getText().toString() , type, cursor);

            }
        });


        switchbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                {
                    type = "annonymous" ;
                }else
                {
                    type = "exist" ;
                }
            }
        });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundtext.setVisibility(View.VISIBLE);
            }
        });

        backgroundtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundtext.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void showLoading() {

        progressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {

        progressbar.setVisibility(View.GONE);

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void Result(Boolean message) {
        if (message)
        {
            Toast.makeText(this, "تم اضافه السؤال بنجاح", Toast.LENGTH_SHORT).show();
            text.setText("");
            backgroundtext.setVisibility(View.GONE);
        }

    }

    @Override
    public void puplicQuestion(List<puplicQuestion.Question> questions) {

        if (flag)
        {
            fab.setVisibility(View.VISIBLE);

            RecyclerViewHomeAdapterQuestion headerAdapter = new RecyclerViewHomeAdapterQuestion(questions, this);
            recyclerView.setAdapter(headerAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            headerAdapter.notifyDataSetChanged();
        }
        else
        {
            fab.setVisibility(View.GONE);

            RecyclerViewHomeAdapterQuestionPrivate headerAdapter = new RecyclerViewHomeAdapterQuestionPrivate(questions, this);
            recyclerView.setAdapter(headerAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            headerAdapter.notifyDataSetChanged();
        }





    }

    @Override
    public void Resultofreplay(String res) {

    }

    @Override
    public void getcurrentprofile(getcurrentprofile.User users) {

    }

    @Override
    public void deleteQuestion(String deleteQuestion) {

    }
}
