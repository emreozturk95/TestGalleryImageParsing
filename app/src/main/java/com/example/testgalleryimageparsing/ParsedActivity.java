package com.example.testgalleryimageparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class ParsedActivity extends AppCompatActivity {

    ImageView ivDetailImage;
    TextView tvDetailName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsed);

        ivDetailImage = findViewById(R.id.ivDetailImage);
        tvDetailName = findViewById(R.id.tvDetailName);


        SharedPreferences sharedPreferences = getSharedPreferences("com.example.testgalleryimageparsing", Context.MODE_PRIVATE);

        String savedString = sharedPreferences.getString("bitmap","");
        String savedName = sharedPreferences.getString("name", "");


        tvDetailName.setText(savedName);
        /*ivDetailImage.setBackground(stringToBitMap(savedString));*/

        ivDetailImage.setImageBitmap(stringToBitMap(savedString));


    }

    public Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
