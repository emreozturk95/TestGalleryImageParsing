package com.example.testgalleryimageparsing;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class MyDataClass implements Parcelable{

    private String name;
    private Uri base64;

    public void setName(String name) {
        this.name = name;
    }

    public void setBase64(Uri base64) {
        this.base64 = base64;
    }

    public MyDataClass(String name, Uri base64)  {
        this.name = name;
        this.base64 = base64;
    }


    protected MyDataClass(Parcel in) {
        name = in.readString();
        base64 = Uri.parse(in.readString());
    }

    public static final Creator<MyDataClass> CREATOR = new Creator<MyDataClass>() {
        @Override
        public MyDataClass createFromParcel(Parcel in) {
            return new MyDataClass(in);
        }

        @Override
        public MyDataClass[] newArray(int size) {
            return new MyDataClass[size];
        }
    };

    public String getName() {
        return name;
    }

    public Uri getBase64() {
        return base64;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(String.valueOf(base64));
    }
}
