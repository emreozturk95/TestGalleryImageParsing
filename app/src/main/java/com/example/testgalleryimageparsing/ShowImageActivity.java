package com.example.testgalleryimageparsing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class ShowImageActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ArrayList<MyDataClass> arrayList;
    ArrayList<Bitmap> imageArray;
    ArrayList<String> nameArray;
    ListView listView;
    ArrayAdapter arrayAdapter;
    Bitmap bitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        listView = findViewById(R.id.listView);

        arrayList = new ArrayList<>();
        imageArray = new ArrayList<Bitmap>();
        nameArray = new ArrayList<String>();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.testgalleryimageparsing", Context.MODE_PRIVATE);

        String savedBitmapToString = sharedPreferences.getString("bitmap", "");

        String savedName = sharedPreferences.getString("name", "");

        Bitmap bitmapImage = StringToBitMap(savedBitmapToString);

        arrayList.add(new MyDataClass(bitmapImage, savedName));

        MyAdapter adapter = new MyAdapter(this, arrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*sharedPreferences = getPreferences(Context.MODE_PRIVATE);öö
                SharedPreferences.Editor editor = sharedPreferences.edit();
                sharedPreferences.edit().putString("asd", String.valueOf(bitmap)).apply();
                editor.apply();*/

                Intent intent = new Intent(ShowImageActivity.this, ParsedActivity.class);
                startActivity(intent);

            }
        });

    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


}
