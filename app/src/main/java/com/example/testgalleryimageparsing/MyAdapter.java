package com.example.testgalleryimageparsing;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    RecyclerViewClickListener recyclerViewClickListener;
    Context context;
    List<MyDataClass> arr;

    public MyAdapter(Context context, List<MyDataClass> arr, RecyclerViewClickListener recyclerViewClickListener) {
        this.context = context;
        this.arr = arr;
        this.recyclerViewClickListener = recyclerViewClickListener;

    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false);
        ViewHolder holder = new ViewHolder(view, recyclerViewClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        MyDataClass myDataClass = arr.get(position);
        holder.showData(myDataClass, position);

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivImage;
        TextView tvName;
        private RecyclerViewClickListener recyclerViewClickListener;

        public ViewHolder(@NonNull View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.recyclerViewClickListener = recyclerViewClickListener;

            ivImage = itemView.findViewById(R.id.ivItemImage);
            tvName = itemView.findViewById(R.id.tvItemName);

        }

        @Override
        public void onClick(View view) {

            if(recyclerViewClickListener != null){
                recyclerViewClickListener.onItemClick(getAdapterPosition());
            }

            Log.d("onItemClicked"," itemPos : "+getAdapterPosition());

        }

        public void showData(MyDataClass myDataClass, int position) {

            this.tvName.setText(myDataClass.getName());
            this.ivImage.setImageURI(Uri.parse((myDataClass.getBase64())));
//            this.ivImage.setImageBitmap(BitMapUtil.stringToBitMap(myDataClass.getBase64()));

        }
    }
}
