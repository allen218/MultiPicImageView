package com.example.mac.multipicimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private MultiPicImageView mPic;

    @Bind(R.id.four_btn)
    Button four_btn;

    @Bind(R.id.five_btn)
    Button five_btn;

    @Bind(R.id.six_btn)
    Button six_btn;

    @Bind(R.id.nine_btn)
    Button nine_btn;

    private List<Bitmap> mBitmapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();

        initRxBinding();

    }

    private void initView() {
        mPic = (MultiPicImageView) findViewById(R.id.iv);

    }

    private void initRxBinding() {
        RxView.clicks(four_btn).subscribe(this::clickFourBtn);
        RxView.clicks(five_btn).subscribe(this::clickFiveBtn);
        RxView.clicks(six_btn).subscribe(this::clickSixBtn);
        RxView.clicks(nine_btn).subscribe(this::clickNineBtn);
    }

    /**
     * 点击4张图片
     *
     * @param oVoid
     */

    private void clickFourBtn(Void oVoid) {
        createShowData(4);
    }

    /**
     * 点击5张图片
     *
     * @param oVoid
     */
    private void clickFiveBtn(Void oVoid) {
        createShowData(5);

    }

    /**
     * 点击6张图片
     *
     * @param oVoid
     */
    private void clickSixBtn(Void oVoid) {
        createShowData(6);

    }

    /**
     * 点击9张图片
     *
     * @param oVoid
     */
    private void clickNineBtn(Void oVoid) {

        createShowData(9);
    }

    /**
     * 创建图片数据
     *
     * @param num
     */
    private void createShowData(int num) {
        if (mBitmapList == null) {
            mBitmapList = new ArrayList<>();
        } else {
            if (mBitmapList.size() > num) {
                removeListData(mBitmapList, num);
            } else {
                addListData(mBitmapList, num);
            }
        }
        mPic.addImg(mBitmapList);
    }

    /**
     * 移除List元素
     *
     * @param mBitmapList
     * @param end
     */
    private int mBitListNum;

    private void removeListData(List<Bitmap> mBitmapList, int end) {
        mBitListNum = mBitmapList.size();
        for (int x = end; x < mBitListNum; x++) {
            mBitmapList.remove(mBitmapList.size() - 1);
        }
    }

    /**
     * 添加元素
     *
     * @param mBitmapList
     * @param end
     */
    private void addListData(List<Bitmap> mBitmapList, int end) {
        for (int x = mBitmapList.size(); x < end; x++) {
            mBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.a2));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        /*final List<Bitmap> mData = createData();
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
        }).start();*/
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
