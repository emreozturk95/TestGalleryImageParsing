package com.example.testgalleryimageparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = getIntent();
        MyDataClass myDataClass = intent.getParcelableExtra("list");

        if (myDataClass != null) {
            tvDetailName.setText(myDataClass.getName());

//            ivDetailImage.setImageBitmap(new BitMapUtil().stringToBitMap(myDataClass.getBase64()));
//            ivDetailImage.setImageURI(m);
        }


    }
}
