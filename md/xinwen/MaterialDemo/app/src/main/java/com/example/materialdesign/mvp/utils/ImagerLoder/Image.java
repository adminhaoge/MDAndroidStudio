package com.example.materialdesign.mvp.utils.ImagerLoder;

import android.graphics.Bitmap;

public class Image {
    private String url;
    private Bitmap mBitmap;

    public Image(String url, Bitmap mBitmap) {
        this.url = url;
        this.mBitmap = mBitmap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
}
