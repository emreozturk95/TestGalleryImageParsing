package com.example.testgalleryimageparsing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyboardShortcutGroup;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PickImageActivity extends AppCompatActivity {

    ImageView ivImage;
    EditText etName;
    Button btnSave;
    private Uri imageUri;
    Bitmap bitmap;
    String base64String;
    String stringUri;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {

            case 1:

                if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

                    imageUri = data.getData();

                    stringUri = imageUri.toString();

                    try {

//                        URI TO BITMAP --------
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        bitmap = BitmapFactory.decodeStream(inputStream);
//                        URI TO BITMAP --------

//                        BITMAP TO STRING ---------

                        ivImage.setImageURI(imageUri);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Unable to Open image", Toast.LENGTH_SHORT).show();
                    }

                }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        ivImage = findViewById(R.id.imageView);
        etName = findViewById(R.id.etText);
        btnSave = findViewById(R.id.btnSave);

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(PickImageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(PickImageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);

                } else {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);

                }

            }

        });

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {

                    Intent returnIntent = new Intent();

                    /*

                    YÖNTEM 1

                    returnIntent.putExtra("resultbase64", base64String.toString());
                    returnIntent.putExtra("resultName", etName.getText().toString());

                     */


                    SharedPreferences sharedPref = PickImageActivity.this.getSharedPreferences("EMRE OZTURKKKKK", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    //                    ----------------------------------------------------


                    String json = sharedPref.getString("jsonList", null);
                    List<MyDataClass> myDataClassList;

                    if (json == null) {
                        myDataClassList = new ArrayList<>();

                    } else {
                        Gson gson = new Gson();


                        Type type = new TypeToken<List<MyDataClass>>(){}.getType();
                        myDataClassList = gson.fromJson(json,type);


                        /*MyDataClass[] myDataClasses = gson.fromJson(json, MyDataClass[].class);
                        myDataClassList = Arrays.asList(myDataClasses);*/




                    }

                    myDataClassList.add(new MyDataClass(etName.getText().toString(), stringUri));

                    json = new Gson().toJson(myDataClassList);

                    editor.putString("jsonList", json);

                    editor.apply();

//                    ----------------------------------------------------

                    MyDataClass myDataClass = new MyDataClass(etName.getText().toString(), stringUri);
                    returnIntent.putExtra("asd", myDataClass);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(PickImageActivity.this, "Fotoğraf seçilmedi", Toast.LENGTH_SHORT).show();


                }


            }
        });


    }


}
