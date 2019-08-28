package com.example.testgalleryimageparsing;

import android.content.Intent;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewClickListener {


    List<MyDataClass> arrayList = new ArrayList<>();
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    CardView cardView;
    TextView tvBaslik;
    Bitmap bitmap;

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
