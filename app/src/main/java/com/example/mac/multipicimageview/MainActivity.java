package com.example.mac.multipicimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MultiPicImageView mPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mPic = (MultiPicImageView) findViewById(R.id.iv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        final List<Bitmap> mData = createData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mPic.addImg(mData);

            }
        }).start();
    }

    @NonNull
    private List<Bitmap> createData() {
        final List<Bitmap> mData = new ArrayList<>();
        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a2));
        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a3));
        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a4));
        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a5));
//        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a6));
//        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a7));
//        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a8));
//        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a9));
//        mData.add(BitmapFactory.decodeResource(getResources(), R.drawable.a10));
        return mData;
    }
}
