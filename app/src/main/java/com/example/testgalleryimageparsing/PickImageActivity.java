package com.example.testgalleryimageparsing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PickImageActivity extends AppCompatActivity {

    ImageView ivImage;
    EditText etName;
    Button btnSave;
    private Uri imageUri;
    Bitmap bitmap;


    public String bitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();

            InputStream inputStream;

            try {

                inputStream = getContentResolver().openInputStream(imageUri);

                bitmap = BitmapFactory.decodeStream(inputStream);

                ivImage.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "unable to open image", Toast.LENGTH_SHORT).show();
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

                final SharedPreferences sharedPreferences = getSharedPreferences("com.example.testgalleryimageparsing", Context.MODE_PRIVATE);

                final SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("bitmap", bitMapToString(bitmap));


                etName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                        sharedPreferences.edit().putString("autoSave", editable.toString()).apply();

                    }
                });





//                editor.putString("name", etName);

                editor.apply();

                Intent intent = new Intent(PickImageActivity.this, ShowImageActivity.class);

                startActivity(intent);


            }
        });


    }
}
