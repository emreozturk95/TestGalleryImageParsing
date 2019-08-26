package com.example.testgalleryimageparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<MyDataClass> arr;

    public MyAdapter(Context context, ArrayList<MyDataClass> arr) {
        this.context = context;
        this.arr = arr;
    }




    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.item_picture,viewGroup,false);

        ImageView img = view.findViewById(R.id.ivItemImage);
        TextView  tv = view.findViewById(R.id.tvItemName);

        img.setImageBitmap(arr.get(i).getImage());
        tv.setText(arr.get(i).getName());

        return view;
    }
}
