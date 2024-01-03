package com.bianki.biankiapp.CreateGroup;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.spinnergroup;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class creategroup extends AppCompatActivity implements HomeView {

    Spinner type ;
    Spinner prv ;
    String typeofgroup ;
    String prvgroup  ;
    Button creategroup ;
    ImageView image ;
    LottieAnimationView lottieAnimationView ;
    EditText nameofgroup ;
    String mediaPath = "";
    CreatePresenter createPresenter ;
    Helper helper ;
    String cursor ;
    ImageView back ;
    LinearLayout cover ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creategroup);

        type = findViewById(R.id.type);
        prv = findViewById(R.id.privacygroup);
        nameofgroup = findViewById(R.id.namegroup);
        lottieAnimationView = findViewById(R.id.progressBar);
        creategroup = findViewById(R.id.creategroup);
        image = findViewById(R.id.image);
        createPresenter = new CreatePresenter(this);
        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();
        lottieAnimationView = findViewById(R.id.progressBar);
        lottieAnimationView.setVisibility(View.GONE);
        back = findViewById(R.id.back);
        cover = findViewById(R.id.cover);




        nameofgroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (null != nameofgroup.getLayout() && nameofgroup.getLayout().getLineCount() > 2) {
                    nameofgroup.getText().delete(nameofgroup.getText().length() - 1, nameofgroup.getText().length());
                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        List<spinnergroup> list  = new ArrayList<>();
        list.add(new spinnergroup("Sport"));
        list.add(new spinnergroup("Books"));
        list.add(new spinnergroup("Music"));
        list.add(new spinnergroup("Cinema"));
        list.add(new spinnergroup("Games"));
        list.add(new spinnergroup("Education"));
        list.add(new spinnergroup("Other"));



        List<spinnergroup> list2  = new ArrayList<>();
        list2.add(new spinnergroup("public"));
        list2.add(new spinnergroup("Secrt"));
        list2.add(new spinnergroup("closed"));

        ArrayAdapter<spinnergroup> arrayAdapter
                = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<spinnergroup> arrayAdapter2
                = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , list2);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(arrayAdapter);
        prv.setAdapter(arrayAdapter2);




        prv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                spinnergroup s = (spinnergroup)adapterView.getSelectedItem();
                prvgroup = s.getText();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                spinnergroup s = (spinnergroup)adapterView.getSelectedItem();
                typeofgroup = s.getText();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                        return;

                    }

                } else {
                    Intent pictureActionIntent = new Intent(
                            Intent.ACTION_PICK);
                    pictureActionIntent.setType("image/*");


                    startActivityForResult(pictureActionIntent, 0);
                }

            }
        });


        creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameofgroup.getText().toString();
                if (name.isEmpty())
                {
                    nameofgroup.setError("يحب ادخال اسم الجروب");
                }
                else
                {

                    RequestBody n = RequestBody.create(MediaType.parse("text/plain"),
                            name);

                    RequestBody type = RequestBody.create(MediaType.parse("text/plain"),
                            typeofgroup);

                    RequestBody prev = RequestBody.create(MediaType.parse("text/plain"),
                            prvgroup);

                    RequestBody c = RequestBody.create(MediaType.parse("text/plain"),
                            cursor);

                    Map<String , RequestBody > map = uploadFile();

                    createPresenter.CreateGroup(n , type , prev  , map , c);



                }
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {


                Uri selectedImage = data.getData();


                ContentResolver cr = this.getContentResolver();


                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    image.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cover.setVisibility(View.GONE);
                    cursor.close();


                } // When an Video is picked



            else{
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch(Exception e){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Intent pictureActionIntent = new Intent(
                            Intent.ACTION_PICK);
                    pictureActionIntent.setType("image/*");


                    startActivityForResult(pictureActionIntent, 0);


                } else {

                    Toast.makeText(getApplicationContext(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }


                return;
            }


        }
    }


    private Map<String, RequestBody> uploadFile() {

        if (mediaPath.isEmpty())
        {
            Map<String, RequestBody> map  =  new HashMap<>();
            return  map ;
        }
        else {


            Map<String, RequestBody> map = new HashMap<>();
            File file = new File(mediaPath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

            return map;
        }


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
    public void result(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        if(s.equals("Group created successfully "))
        {
            finish();
        }

    }
}
