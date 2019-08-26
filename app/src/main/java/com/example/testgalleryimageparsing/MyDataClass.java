package com.example.testgalleryimageparsing;

import android.graphics.Bitmap;

public class MyDataClass {

    Bitmap image;
    String name;


    public MyDataClass(Bitmap image, String name) {
        this.image = image;
        this.name = name;
    }


    @Override
    public String toString() {
        return "MyDataClass{" +
                "image=" + image +
                ", name='" + name + '\'' +
                '}';
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
