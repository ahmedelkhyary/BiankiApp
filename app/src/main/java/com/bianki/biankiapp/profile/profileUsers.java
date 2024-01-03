package com.bianki.biankiapp.profile;

import android.content.Intent;
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
import com.bianki.biankiapp.Adapter.Adapterprofiletwoimage;
import com.bianki.biankiapp.Adapter.HomeAdapterprofile;
import com.bianki.biankiapp.ClassModel.getMediaById;
import com.bianki.biankiapp.ClassModel.getUserById;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.Fullimage.Fullimage;
import com.bianki.biankiapp.Fullscreenimage;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.videoplyer;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class profileUsers extends AppCompatActivity implements HomeView {

    profileUsersPresenter profileUsersPresenter ;
    Helper helper ;
    String cursor ;
    ImageView back;
    CircularImageView imageView ;
    TextView fullname ;
    TextView username ;
    TextView following ;
    TextView followers ;
    TextView postes ;
    RecyclerView recyclerView;
    LottieAnimationView progressBar ;
    TextView bio ;
    ImageView grid ;
    ImageView slide ;
    Button follow ;
    GridLayoutManager layoutManager ;
    Adapterprofiletwoimage homeAdapter;
    LinearLayout Linear ;
    LinearLayout backgroundtext ;
    EditText text ;
    Button post ;
    SwitchCompat switchbutton ;
    String type = "exist" ;
    Button ask ;
    String id ;
    int x ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_users);


        profileUsersPresenter = new profileUsersPresenter(this);
        helper = new Helper(this);
        cursor = helper.getiddata();


        backgroundtext = findViewById(R.id.backgroundtext);
        text = findViewById(R.id.gettext);
        switchbutton = findViewById(R.id.switchbutton);
        post = findViewById(R.id.post);
        ask = findViewById(R.id.ask);
        back = findViewById(R.id.back );
        imageView = findViewById(R.id.imageView);
        fullname = findViewById(R.id.nameofuser);
        username = findViewById(R.id.nameofusername);
        following = findViewById(R.id.numberoffollowing);
        followers = findViewById(R.id.numberoffolloers);
        postes = findViewById(R.id.numberofpostes);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        bio = findViewById(R.id.bio);
        follow = findViewById(R.id.follow);
        grid = findViewById(R.id.grid);
        slide = findViewById(R.id.slide);
        Linear = findViewById(R.id.Linear);
        x = 1 ;

        Intent intent = getIntent();
        id = intent.getStringExtra("id") ;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundtext.setVisibility(View.VISIBLE);
            }
        });



        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (text.getText().toString().isEmpty())
                {
                    return;
                }else

                profileUsersPresenter.privateAsk(text.getText().toString() ,id,type, cursor);

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

        backgroundtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundtext.setVisibility(View.GONE);
            }
        });





        profileUsersPresenter.userSearch(id  , cursor);
        profileUsersPresenter.getMediaById(id);

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileUsersPresenter.follow(id , cursor);
            }
        });


        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                grid.setImageResource(R.drawable.gridlight);
                slide.setImageResource(R.drawable.slidedark);

                profileUsersPresenter.getMediaById(id);
                x = 2 ;


            }




        });

        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                grid.setImageResource(R.drawable.grid);
                slide.setImageResource(R.drawable.slide);

                profileUsersPresenter.getMediaById(id);
                x = 1 ;



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
    public void Resultusers(getUserById.User users) {

        Linear.setVisibility(View.VISIBLE);

        Boolean check = users.getFollowed();

        if (check)
        {
            follow.setText("FOLLOWING");
        }else
        {
            follow.setText("FOLLOW");

        }


        String photo = users.getProfileImage();
        try{
            Picasso.get().load(photo).placeholder(R.drawable.avatar).into(imageView);

        }catch (Exception e){}


        String fullnameintent = users.getFullName() ;
        String  usernameintent = users.getUserName();
        String  biointent = users.getBio();

        fullname.setText(fullnameintent);
        username.setText(usernameintent);

        String localfollowinf = String.valueOf(users.getFollowingNum());
        String localfolloers = String.valueOf(users.getFollowersNum());
        String localpostes = String.valueOf(users.getPostsNum());
        following.setText(localfollowinf);
        followers.setText(localfolloers);
        postes.setText(localpostes);
        bio.setText(biointent);

    }

    @Override
    public void posts(List<getMediaById.File> posts) {



        if (x == 1)
        {
            homeAdapter = new Adapterprofiletwoimage(posts, this);
            recyclerView.setAdapter(homeAdapter);
            layoutManager = new GridLayoutManager(this, 2,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            homeAdapter.notifyDataSetChanged();

            homeAdapter.setOnItemClickListener((view, position) -> {


                if (posts.get(position).getPostType().equals("video"))
                {
                    Intent intent = new Intent(getApplicationContext() , videoplyer.class);
                    intent.putExtra("video" , posts.get(position).getPath());

                    startActivity(intent);

                }else

                {
                    Intent intent = new Intent(getApplicationContext() , Fullimage.class);

                    intent.putExtra("id" , posts.get(position).getId());
                    intent.putExtra("text" , posts.get(position).getText());
                    intent.putExtra("path" , posts.get(position).getPath());
                    intent.putExtra("type" , posts.get(position).getPostType());
                    intent.putExtra("like" , posts.get(position).getLikesNumber().toString());
                    intent.putExtra("comment" , posts.get(position).getCommentsNumber().toString());
                    intent.putExtra("islike" , posts.get(position).getIsLiked().toString());


                    startActivity(intent);
                }



            });
        }else
        {
            HomeAdapterprofile homeAdapter = new HomeAdapterprofile(posts, this);
            recyclerView.setAdapter(homeAdapter);
            layoutManager = new GridLayoutManager(this, 1,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            homeAdapter.notifyDataSetChanged();

            homeAdapter.setOnItemClickListener((view, position) -> {

                if (posts.get(position).getPostType().equals("video"))
                {
                    Intent intent = new Intent(getApplicationContext() , videoplyer.class);
                    intent.putExtra("video" , posts.get(position).getPath());

                    startActivity(intent);

                }


                else if (posts.get(position).getPostType().equals("image"))
                {
                    Intent intent = new Intent(getApplicationContext() , Fullscreenimage.class);
                    intent.putExtra("photo" , posts.get(position).getPath());
                    startActivity(intent);
                }});


        }


    }

    @Override
    public void follow(String s) {


        if (s.equals("following added successfully"))
        {
            follow.setText("FOLLOWING");
            int x = Integer.parseInt(followers.getText().toString());
            x++ ;
            String newfollow = String.valueOf(x);

            followers.setText(newfollow);

        }else if (s.equals("following removed successfully"))
        {
            follow.setText("FOLLOW");

            int x = Integer.parseInt(followers.getText().toString());
            x-- ;
            String newfollow = String.valueOf(x);

            followers.setText(newfollow);


        }

    }

    @Override
    public void ResultofAsk(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        if (s.equals("question added successfully"))
        {
            text.setText("");
            backgroundtext.setVisibility(View.GONE);
            startActivity(new Intent(getApplicationContext() , com.bianki.biankiapp.ASK.ask.class));
        }
    }

}
