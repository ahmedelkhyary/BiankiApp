package com.bianki.biankiapp.Login;

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
import com.bianki.biankiapp.HomePage.Home;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.Singup.Singup;
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

public class login extends AppCompatActivity implements HomeView {

    EditText email;
    EditText password;
    Button login;
    TextView sginup;
    LoginPresenter loginPresenter;
    CallbackManager callbackManager;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    LottieAnimationView progressBar;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    Button signInButton;
    Button loginButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        sginup = findViewById(R.id.sginup);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        TextView emailloginname = findViewById(R.id.emaillogin);
        TextView passwordlogin = findViewById(R.id.passwordlogin);
        TextView or = findViewById(R.id.or);




         signInButton = findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
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


        sp = getApplicationContext().getSharedPreferences("login", 0);
        speditor = sp.edit();
        if (sp.getBoolean("logged", false)) {

            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }


        sginup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Singup.class));
            }
        });

        loginPresenter = new LoginPresenter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String localemail = email.getText().toString();
                String localpassword = password.getText().toString();


                boolean flag = isEmailValid(localemail);

                if (!flag) {
                    email.setError("Invalid Email");
                }
                 else if (localpassword.isEmpty()) {
                    password.setError("Require");
                } else {
                    loginPresenter.loginmodel(localemail, localpassword);

                }
            }
        });



        callbackManager = CallbackManager.Factory.create();
         loginButton = findViewById(R.id.login_button);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(login.this, Arrays.asList("public_profile", "email"));
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

                    }
                });
            }
        });


        if(Locale.getDefault().getLanguage().equals("ar"))
        {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Tajawal-Medium.ttf");
            emailloginname.setTypeface(MLight);
            passwordlogin.setTypeface(MLight);
            login.setTypeface(MLight);
            sginup.setTypeface(MLight);
            or.setTypeface(MLight);
            signInButton.setTypeface(MLight);
            loginButton.setTypeface(MLight);



        }else
        {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
            Typeface MLight2 = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Semibold.otf");

            emailloginname.setTypeface(MLight);
            passwordlogin.setTypeface(MLight);
            login.setTypeface(MLight2);
            sginup.setTypeface(MLight);
            or.setTypeface(MLight2);
            signInButton.setTypeface(MLight);
            loginButton.setTypeface(MLight);
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


            loginPresenter.googAndFaceAuth(emailface, firstname + lastname, firstname + lastname, profile_pic.toString());

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


            sp = getApplicationContext().getSharedPreferences("login", 0);
            speditor = sp.edit();
            speditor.putBoolean("logged", true);
            speditor.apply();


            startActivity(new Intent(getApplicationContext(), Home.class));


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
            } else {

                localphoto = "https://binki.herokuapp.com/api/users/file/86e0ad062dfe645bf381854cc423f0e0.jfif";

            }


            String localemail = account.getEmail();
            String firstname = account.getGivenName();
            String lastname = account.getFamilyName();

            loginPresenter.googAndFaceAuth(localemail, firstname + " " + lastname, firstname + lastname, localphoto);


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
