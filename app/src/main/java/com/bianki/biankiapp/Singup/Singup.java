package com.bianki.biankiapp.Singup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.success;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Singup extends AppCompatActivity implements HomeView {

    EditText fullname;
    EditText username;
    EditText email;
    EditText password;
    Button register;
    LottieAnimationView progressBar ;
    SginupPresenter sginupPresenter;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    CallbackManager callbackManager;
    String key = "" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        sginupPresenter = new SginupPresenter(this);

        TextView fullnamefont = findViewById(R.id.fullnamefont);
        TextView usernamefont = findViewById(R.id.usernamefont);
        TextView emailfont = findViewById(R.id.emailfont);
        TextView passwordfont = findViewById(R.id.passwordfont);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String localfullname = fullname.getText().toString();
                String localusername = username.getText().toString();
                String localemail = email.getText().toString();
                String localpassword = password.getText().toString();

                boolean flag = isEmailValid(localemail);

                if (!flag) {
                    email.setError("Invalid Email");
                } else if (localfullname.isEmpty()) {
                    fullname.setError("الرجاء ادخال البيانات");
                } else if (localusername.isEmpty()) {
                    username.setError("الرجاء ادخال البيانات");
                } else if (localpassword.isEmpty()) {
                    password.setError("الرجاء ادخال البيانات");
                } else {
                    sginupPresenter.sginup(localfullname, localusername, localemail, localpassword);

                }


            }
        });


        Button signInButton = findViewById(R.id.googlesgin);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.googlesgin:
                        signIn();
                        break;
                    // ...
                }
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        callbackManager = CallbackManager.Factory.create();
        Button loginButton = findViewById(R.id.facebooksgin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(Singup.this, Arrays.asList("public_profile", "email"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        String accessToken = loginResult.getAccessToken().getToken();
                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.e("Response", response.toString());
                                getData(object);

                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,email,first_name,last_name"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
                        graphRequest.setParameters(parameters);
                        graphRequest.executeAsync();
                    }


                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                        Toast.makeText(getApplicationContext(), "لا يوجد اتصال بالانترنت", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });


        if (Locale.getDefault().getLanguage().equals("ar")) {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Tajawal-Medium.ttf");
            fullnamefont.setTypeface(MLight);
            usernamefont.setTypeface(MLight);
            emailfont.setTypeface(MLight);
            passwordfont.setTypeface(MLight);
            register.setTypeface(MLight);
            loginButton.setTypeface(MLight);
            signInButton.setTypeface(MLight);


        } else {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
            Typeface MLight2 = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Semibold.otf");

            fullnamefont.setTypeface(MLight);
            usernamefont.setTypeface(MLight);
            emailfont.setTypeface(MLight);
            passwordfont.setTypeface(MLight);
            loginButton.setTypeface(MLight);
            signInButton.setTypeface(MLight);


        }
    }



    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void getData(JSONObject object) {
        try {
            URL profile_pic = new URL("https://graph.facebook.com/" + object.getString("id") + "/picture?width=250&height=250");



            String emailface = object.getString("email");
            String firstname = object.getString("first_name");
            String lastname = object.getString("last_name");


            sginupPresenter.googAndFaceAuth(emailface, firstname + lastname, firstname + lastname, profile_pic.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onErrorLoading(String message) {

        Toast.makeText(this, "لا يوجد اتصال بالانترنت", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void service(String result, Boolean message) {

        if (message) {


            Helper helper;

            helper = new Helper(getApplicationContext());

            helper.insertid(result);

            sp=getApplicationContext().getSharedPreferences("login", 0);
            speditor=sp.edit();
            speditor.putBoolean("logged",true);
            speditor.apply();



            key = "sgin" ;

            Intent intentforsgin = new Intent(getApplicationContext(),success.class);
            intentforsgin.putExtra("key" , key);
           startActivity(intentforsgin);

           finish();


        } else

            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void servicefacebookandgoogle(String result, Boolean message) {


        if (message) {


            Helper helper;

            helper = new Helper(getApplicationContext());

            helper.insertid(result);

            sp=getApplicationContext().getSharedPreferences("login", 0);
            speditor=sp.edit();
            speditor.putBoolean("logged",true);
            speditor.apply();


            key = "faceookandgoogle" ;
            Intent intentforfaceandgoogle = new Intent(getApplicationContext(),success.class);
            intentforfaceandgoogle.putExtra("key" , key);
             startActivity(intentforfaceandgoogle);


            finish();


        } else

            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        String localphoto;
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            if (account.getPhotoUrl() != null) {
                localphoto = account.getPhotoUrl().toString();
                Log.e("Lpcal" , localphoto);
            } else {

                localphoto = "https://binki.herokuapp.com/api/users/file/86e0ad062dfe645bf381854cc423f0e0.jfif";

            }




            String localemail = account.getEmail();
            String firstname = account.getGivenName();
            String lastname = account.getFamilyName();

            sginupPresenter.googAndFaceAuth(localemail, firstname + " " + lastname, firstname + lastname, localphoto);


            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("handleSignInResult", "signInResult:failed code=" + e.getStatusCode());
        }
    }



    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
