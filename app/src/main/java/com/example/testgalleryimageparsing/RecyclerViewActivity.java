package com.example.testgalleryimageparsing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewClickListener {


    List<MyDataClass> arrayList = new ArrayList<>();
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    CardView cardView;
    TextView tvBaslik;


    private final int REQUEST = 23424;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {

            case REQUEST:

                if (resultCode == RESULT_OK && data != null) {


                    /* YÖNTEM 1
                        String base64 = data.getStringExtra("resultbase64");
                        String name = data.getStringExtra("resultName");
                        arrayList.add(new MyDataClass(name,base64));
                     */


                    MyDataClass data2 = data.getParcelableExtra("asd");

                    arrayList.add(data2);
                    myAdapter.notifyDataSetChanged();


                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);
        cardView = findViewById(R.id.cardView);
        tvBaslik = findViewById(R.id.tvBaslik);

        arrayList = new ArrayList<>();

        myAdapter = new MyAdapter(getApplicationContext(), arrayList, this);
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

//        ----------------------------------------------


//        URI TO STRING YAPMAK LAZIM

        SharedPreferences sharedPref = getSharedPreferences("EMRE OZTURKKKKK", Context.MODE_PRIVATE);

        String getJson = sharedPref.getString("jsonList", null);
        if (getJson == null) {
            return;
        }

        Gson gson = new Gson();

      /*  MyDataClass[] myDataClasses = gson.fromJson(getJson, MyDataClass[].class);

        List<MyDataClass> myDataClassList2 = Arrays.asList(myDataClasses);

        */


        Type type = new TypeToken<List<MyDataClass>>(){}.getType();
        List<MyDataClass> myDataClass = gson.fromJson(getJson,type);

        arrayList.addAll(myDataClass);

//        List<MyDataClass> myDataClass = gson.fromJson(getJson,MyDataClass.class);

//        arrayList.addAll(myDataClassList2);

        myAdapter.notifyDataSetChanged();


    }


    @Override
    public void onItemClick(int position) {

        if (position < arrayList.size() && position > -1) {

            Intent intent = new Intent(this, ParsedActivity.class);

            startActivity(intent);

        }

    }


    public void fotoSec(View v) {

        Intent intent = new Intent(RecyclerViewActivity.this, PickImageActivity.class);
        startActivityForResult(intent, REQUEST);

    }


}
