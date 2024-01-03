package com.bianki.biankiapp.Search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterUsers;
import com.bianki.biankiapp.ClassModel.userSearch;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.EndlessParentScrollListener;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.profile.profileUsers;

import java.util.ArrayList;
import java.util.List;

public class SearchUser extends AppCompatActivity implements HomeView {

    EditText search ;
    ImageView back ;
    SearchPresenter searchPresenter ;
    RecyclerView recyclerView ;
    LottieAnimationView progressBar ;
    Helper helper ;
    String cursor ;
    NestedScrollView nestedSV ;
    int x = 1 ;
    int flag = 0 ;

    List<userSearch.User> getMedaiaLocal;
    LinearLayoutManager LinearLayoutManager ;
    RecyclerViewHomeAdapterUsers homeAdapter ;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        search = findViewById(R.id.searchtousers);
        back = findViewById(R.id.back);
        searchPresenter = new SearchPresenter(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        nestedSV = findViewById(R.id.nestedSV);

        helper = new Helper(this);
        cursor = helper.getiddata();

        getMedaiaLocal = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);

        homeAdapter = new RecyclerViewHomeAdapterUsers(getMedaiaLocal, getApplicationContext());
        LinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LinearLayoutManager);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                        getMedaiaLocal.clear();
                       searchPresenter.userSearch(search.getText().toString() , "100" , String.valueOf(x) , cursor);

                    flag = 0 ;

                    return true;
                }
                return false;
            }
        });



        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (search.getRight() - search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        search.setText("");

                        return true;
                    }
                }
                return false;
            }
        });


        nestedSV.setOnScrollChangeListener(new EndlessParentScrollListener(LinearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {

                x++;
                searchPresenter.userSearch(search.getText().toString() , "50", String.valueOf(x), cursor);
                flag = 1 ;
            }
        });
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

    }

    @Override
    public void Result(List<userSearch.User> searches) {



        if (flag ==0)
        {


            recyclerView.setAdapter(homeAdapter);
            homeAdapter.appenddata(searches);
            homeAdapter.notifyDataSetChanged();

            homeAdapter.setOnItemClickListener((view, position) ->
        {
            Intent intent = new Intent(getApplicationContext() , profileUsers.class ) ;
            intent.putExtra("id" , searches.get(position).getId());
            startActivity(intent);
        });
        }else

        {
            homeAdapter.appenddata(searches);
            homeAdapter.notifyDataSetChanged();
        }





    }
}
