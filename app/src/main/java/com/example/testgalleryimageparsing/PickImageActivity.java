package com.example.testgalleryimageparsing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PickImageActivity extends AppCompatActivity {

    ImageView ivImage;
    EditText etName;
    Button btnSave;
    private Uri imageUri;
    Bitmap bitmap;
    String base64String;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {

            case 1:

                if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

                    imageUri = data.getData();

                    InputStream inputStream;

                    try {

                        inputStream = getContentResolver().openInputStream(imageUri);

                        bitmap = BitmapFactory.decodeStream(inputStream);

                        base64String = BitMapUtil.bitMapToString(bitmap);

                        ivImage.setImageURI(imageUri);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "unable to open image", Toast.LENGTH_SHORT).show();
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

                    /*YÖNTEM 1
                    returnIntent.putExtra("resultbase64", base64String.toString());
                    returnIntent.putExtra("resultName", etName.getText().toString());
                     */

                    MyDataClass myDataClass = new MyDataClass(etName.getText().toString(), imageUri);
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
