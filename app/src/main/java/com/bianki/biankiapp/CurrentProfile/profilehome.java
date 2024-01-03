package com.bianki.biankiapp.CurrentProfile;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterprofile;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterprofiletwoimage;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.EditProfile.editprfile;
import com.bianki.biankiapp.Fullimage.Fullimage;
import com.bianki.biankiapp.Fullscreenimage;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.videoplyer;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class profilehome extends AppCompatActivity implements HomeView {
    CurrentprofilePresenter currentprofilePresenter ;
    ImageView back;
    CircularImageView imageView ;
    TextView fullname ;
    TextView username ;
    TextView following ;
    TextView followers ;
    TextView postes ;
    Helper helper ;
    String cursor ;
    Button edit ;
    LottieAnimationView progressBar;
    TextView fontfollowers ;
    TextView fontfollowing ;
    TextView fontpost ;
    RecyclerView recyclerView ;
    ImageView grid ;
    ImageView slide ;

    TextView bio;

    GridLayoutManager layoutManager ;
    RecyclerViewHomeAdapterprofiletwoimage homeAdapter;


    String photoofuserintent ;
    String fullnameintent ;
    String usernameintent ;
    String biointent ;
    int x ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilehome);

        currentprofilePresenter = new CurrentprofilePresenter(this);


        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        back = findViewById(R.id.back );
        imageView = findViewById(R.id.imageView);
        fullname = findViewById(R.id.nameofuser);
        username = findViewById(R.id.nameofusername);
        following = findViewById(R.id.numberoffollowing);
        followers = findViewById(R.id.numberoffolloers);
        postes = findViewById(R.id.numberofpostes);
        edit = findViewById(R.id.editprfile);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);


        grid = findViewById(R.id.grid);
        slide = findViewById(R.id.slide);
        x = 1 ;

        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                grid.setImageResource(R.drawable.gridlight);
                slide.setImageResource(R.drawable.slidedark);

                currentprofilePresenter.getcurrentprofilepost(cursor);
                x = 2 ;


            }




        });

        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    grid.setImageResource(R.drawable.grid);
                    slide.setImageResource(R.drawable.slide);

                currentprofilePresenter.getcurrentprofilepost(cursor);
                x = 1 ;



            }
        });


        bio = findViewById(R.id.bio);

        fontfollowers = findViewById(R.id.fontfollers);
        fontfollowing =findViewById(R.id.fontfollowing);
        fontpost = findViewById(R.id.fontpost);








        edit.setVisibility(View.GONE);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , editprfile.class);

                intent.putExtra("photo" , photoofuserintent);
                intent.putExtra("fullname" , fullnameintent);
                intent.putExtra("username" , usernameintent);
                intent.putExtra("bio" , biointent);

                startActivity(intent);
                overridePendingTransition(R.anim.alphain, R.anim.alphaout);
            }
        });



        if (Locale.getDefault().getLanguage().equals("ar")) {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Tajawal-Medium.ttf");



        } else {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Segoe UI Bold.ttf");
            Typeface MLight2 = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
            Typeface MLight3 = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Medium.otf");

            fullname.setTypeface(MLight);
            username.setTypeface(MLight2);
            followers.setTypeface(MLight2);
            following.setTypeface(MLight2);
            postes.setTypeface(MLight2);
            edit.setTypeface(MLight2);
            fontpost.setTypeface(MLight3);
            fontfollowing.setTypeface(MLight3);
            fontfollowers.setTypeface(MLight3);



        }



        currentprofilePresenter.getcurrentprofile(cursor);
        currentprofilePresenter.getcurrentprofilepost(cursor);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
    public void getcurrentprofile(getcurrentprofile.User users) {

         photoofuserintent = users.getProfileImage();
      //  if (!photoofuserintent.equals(""))

        try{
            Picasso.get().load(photoofuserintent).placeholder(R.drawable.avatar).into(imageView);

        }catch (Exception e){}


         fullnameintent = users.getFullName() ;
         usernameintent = users.getUserName();
         biointent = users.getBio();

        fullname.setText(fullnameintent);
        username.setText(usernameintent);

        String localfollowinf = String.valueOf(users.getFollowingNum());
        String localfolloers = String.valueOf(users.getFollowersNum());
        String localpostes = String.valueOf(users.getPostsNum());
        following.setText(localfollowinf);
        followers.setText(localfolloers);
        postes.setText(localpostes);
        bio.setText(biointent);

        edit.setVisibility(View.VISIBLE);






    }

    @Override
    public void getPosts(List<getMedia.File> getPosts) {


        if (x == 1)
        {
            homeAdapter = new RecyclerViewHomeAdapterprofiletwoimage(getPosts, this);
            recyclerView.setAdapter(homeAdapter);
            layoutManager = new GridLayoutManager(this, 2,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            homeAdapter.notifyDataSetChanged();

            homeAdapter.setOnItemClickListener((view, position) -> {


                if (getPosts.get(position).getPostType().equals("video"))
                {
                    Intent intent = new Intent(getApplicationContext() , videoplyer.class);
                    intent.putExtra("video" , getPosts.get(position).getPath());

                    startActivity(intent);

                }else

                {
                    Intent intent = new Intent(getApplicationContext() , Fullimage.class);

                    intent.putExtra("id" , getPosts.get(position).getId());
                    intent.putExtra("text" , getPosts.get(position).getText());
                    intent.putExtra("path" , getPosts.get(position).getPath());
                    intent.putExtra("type" , getPosts.get(position).getPostType());
                    intent.putExtra("like" , getPosts.get(position).getLikesNumber().toString());
                    intent.putExtra("comment" , getPosts.get(position).getCommentsNumber().toString());
                    intent.putExtra("islike" , getPosts.get(position).getIsLiked().toString());


                    startActivity(intent);
                }



            });
        }else
        {
            RecyclerViewHomeAdapterprofile homeAdapter = new RecyclerViewHomeAdapterprofile(getPosts, this);
            recyclerView.setAdapter(homeAdapter);
            layoutManager = new GridLayoutManager(this, 1,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            homeAdapter.notifyDataSetChanged();

            homeAdapter.setOnItemClickListener((view, position) -> {

                if (getPosts.get(position).getPostType().equals("video"))
                {
                    Intent intent = new Intent(getApplicationContext() , videoplyer.class);
                    intent.putExtra("video" , getPosts.get(position).getPath());

                    startActivity(intent);

                }


                    else if (getPosts.get(position).getPostType().equals("image"))
                    {
                        Intent intent = new Intent(getApplicationContext() , Fullscreenimage.class);
                        intent.putExtra("photo" , getPosts.get(position).getPath());
                        startActivity(intent);
                    }});

        }




    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
