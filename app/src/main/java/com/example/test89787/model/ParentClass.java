package com.example.test89787.model;


import android.net.Uri;

import java.util.ArrayList;

public class ParentClass {

    private String title;
    private final ArrayList<Uri> imageList;

    public ParentClass(String title) {
        this.title = title;
        imageList = new ArrayList<>();
    }

    public void addImage(Uri uri)
    {
        imageList.add(uri);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<Uri> getImageList() {
        return imageList;
    }
}

