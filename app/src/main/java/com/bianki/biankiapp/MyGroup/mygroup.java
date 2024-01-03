package com.bianki.biankiapp.MyGroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bianki.biankiapp.Adapter.MyGroupAdapter;
import com.bianki.biankiapp.ClassModel.myGroups;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.SpecificGroup.SpecificGroup;

import java.util.List;

public class mygroup extends AppCompatActivity implements HomeView {

    MygroupPresenter mygroupPresenter ;
    Helper helper ;
    String cursor ;
    RecyclerView recyclerView ;
    LottieAnimationView lottieAnimationView ;
    ImageView back ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygroup);

        mygroupPresenter = new MygroupPresenter(this);
        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();
        recyclerView = findViewById(R.id.recyclerView);
        lottieAnimationView = findViewById(R.id.progressBar);
        lottieAnimationView.setVisibility(View.GONE);
        back = findViewById(R.id.back);
        mygroupPresenter.myGroups(cursor);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showLoading() {
        lottieAnimationView.setVisibility(View.VISIBLE);


    }

    @Override
    public void hideLoading() {
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Result(List<myGroups.Group> myGroups) {
        MyGroupAdapter homeAdapter = new MyGroupAdapter(myGroups, this);
        recyclerView.setAdapter(homeAdapter);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
        homeAdapter.setOnItemClickListener((view, position) -> {

            Intent intent = new Intent(getApplicationContext() , SpecificGroup.class);
            intent.putExtra("photo" , myGroups.get(position).getPhoto());
            intent.putExtra("name" , myGroups.get(position).getName());
            intent.putExtra("id" , myGroups.get(position).getId());

            startActivity(intent);

        });

    }
}
