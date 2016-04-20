package com.example.mac.multipicimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private MultiPicImageView mPic;

    @Bind(R.id.one_btn)
    Button one_btn;

    @Bind(R.id.two_btn)
    Button two_btn;

    @Bind(R.id.three_btn)
    Button three_btn;

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
        RxView.clicks(one_btn).subscribe(this::clickOneBtn);
        RxView.clicks(two_btn).subscribe(this::clickTwoBtn);
        RxView.clicks(three_btn).subscribe(this::clickThreeBtn);
        RxView.clicks(four_btn).subscribe(this::clickFourBtn);
        RxView.clicks(five_btn).subscribe(this::clickFiveBtn);
        RxView.clicks(six_btn).subscribe(this::clickSixBtn);
        RxView.clicks(nine_btn).subscribe(this::clickNineBtn);
    }

    /**
     * 点击1张图片
     *
     * @param oVoid
     */
    private void clickOneBtn(Void oVoid) {
        createShowData(1);
    }

    /**
     * 点击2张图片
     *
     * @param oVoid
     */
    private void clickTwoBtn(Void oVoid) {
        createShowData(2);
    }

    /**
     * 点击3张图片
     *
     * @param oVoid
     */
    private void clickThreeBtn(Void oVoid) {
        createShowData(3);
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
        }
        if (mBitmapList.size() > num) {
            removeListData(mBitmapList, num);
        } else {
            addListData(mBitmapList, num);
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
}
