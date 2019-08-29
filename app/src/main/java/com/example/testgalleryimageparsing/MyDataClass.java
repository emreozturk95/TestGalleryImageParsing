package com.example.testgalleryimageparsing;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class MyDataClass implements Parcelable {

    private String name;
    private String base64;

    public MyDataClass(String name, String base64) {
        this.name = name;
        this.base64 = base64;
    }


    protected MyDataClass(Parcel in) {
        name = in.readString();
        base64 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(base64);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getBase64() {
        return base64;
    }

}
