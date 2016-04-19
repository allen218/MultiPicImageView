package com.example.mac.multipicimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 16/4/19.
 */
public class MultiPicImageView extends View {
    private List<Bitmap> mBitmapList;
    private Paint mPaint;

    public MultiPicImageView(Context context) {
        this(context, null);
    }

    public MultiPicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#c0c0c0"));
    }

    public void addImg(List<Bitmap> bitmapList) {
        mBitmapList = bitmapList;
        transformImgSize();
    }

    private float transformHeight;
    private float transformWidth;
    private float bitmapTransformWidth;
    private float bitmapTransformHeight;
    private List<Bitmap> mTransformBitmaps;
    private static final float INTERVAL = 10;//间距
    private int currentLineShowNum; //当前行有几张图片

    private void transformImgSize() {
        mTransformBitmaps = new ArrayList<>();
        if (mBitmapList.size() > 1) {
            if (mBitmapList.size() == 4) {
                transformWidth = ((float) mWidth) / 2;
                transformHeight = ((float) mHeight) / 2;
                bitmapTransformWidth = ((float) (mWidth - INTERVAL * 3)) / 2;
                bitmapTransformHeight = ((float) (mHeight - INTERVAL * 3)) / 2;
                currentLineShowNum = 2;
            } else {
                transformWidth = ((float) mWidth) / 3;
                transformHeight = ((float) mHeight) / 3;
                bitmapTransformWidth = ((float) (mWidth - INTERVAL * 4)) / 3;
                bitmapTransformHeight = ((float) (mHeight - INTERVAL * 4)) / 3;
                currentLineShowNum = 3;
            }
        } else {
            transformWidth = mWidth;
            transformHeight = mHeight;
        }

        for (int x = 0; x < mBitmapList.size(); x++) {
            int sourceWidth = mBitmapList.get(x).getWidth();
            int sourceHeight = mBitmapList.get(x).getHeight();

            //计算绽放比例
            float scaleWidth = ((float) bitmapTransformWidth) / sourceWidth;
            float scaleHeight = ((float) bitmapTransformHeight) / sourceHeight;

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);


            //创建新的Bitmap
            Bitmap targetBitmap = Bitmap.createBitmap(mBitmapList.get(x), 0, 0,
                    sourceWidth, sourceHeight, matrix, true);
            mTransformBitmaps.add(targetBitmap);
        }

        postInvalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heithMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthMode, widthSize), measureHeight(heithMode, heightSize));
    }

    private int mWidth;

    private int measureWidth(int widthMode, int widthSize) {
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                throw new RuntimeException("必须为MultiPicImageView指定具体的宽度");

            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
        }
        return mWidth;
    }

    private int mHeight;

    private int measureHeight(int heithMode, int heightSize) {
        switch (heithMode) {
            case MeasureSpec.UNSPECIFIED:
                throw new RuntimeException("必须为MultiPicImageView指定具体的高度");

            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
        }
        return mHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmapList != null) {
            for (int x = 0; x < mBitmapList.size(); x++) {
                int xScale = x % currentLineShowNum;
                int yScale = x / currentLineShowNum;
                float xHeader = 0;
                float yHeader = 0;
                if (xScale == 0) {
                    xHeader = INTERVAL;
                }
                if (yScale == 0) {
                    yHeader = INTERVAL;
                }

                canvas.drawBitmap(mTransformBitmaps.get(x), xScale * bitmapTransformWidth + INTERVAL * (xScale + 1),
                        yScale * bitmapTransformWidth + INTERVAL * (yScale + 1), mPaint);

               /* canvas.drawLine(xScale * bitmapTransformWidth + INTERVAL * (xScale + 1),
                        yScale * bitmapTransformWidth + INTERVAL * (yScale + 1),
                        xScale * bitmapTransformWidth + INTERVAL * (xScale + 1),
                        bitmapTransformHeight, mPaint
                );*/
            }
            moveCanvas(canvas);
        }

    }

    private void moveCanvas(Canvas canvas) {
        if (mBitmapList.size() > 6) {
            //不需要移动
        } else if (mBitmapList.size() > 3) {
            //少一行
            canvas.translate(0, mHeight / 3 / 2);
        } else if (mBitmapList.size() > 1) {
            canvas.translate(0, mHeight / 3);
        }

    }
}
