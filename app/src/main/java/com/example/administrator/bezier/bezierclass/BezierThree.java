package com.example.administrator.bezier.bezierclass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：Tony.Zhou on 2016/11/12 16:12
 * E-mail：781689008@qq.com
 * CSDN :
 * GitHub :
 * If you always so wise, there will be a wonderful life.
 */
public class BezierThree extends View{

    private static final float C = 0.551915024494f;

    private Paint mPaint;
    private int mCenterX,mCenterY;

    private PointF mCenter = new PointF(0,0);
    private float mCircleRadius = 200;
    private float mDifference = mCircleRadius*C;   // 圆形的控制点和数据点的差值

    private float[] mData = new float[8];
    private float[] mCtrl = new float[16];

    private float mDuration = 1000;
    private float mCurrent = 0;
    private float mCount = 100;
    private float mPiece = mDuration/mCount;

    public BezierThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaintAndPoint();
    }

    public BezierThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaintAndPoint();
    }

    public BezierThree(Context context) {
        super(context);
        initPaintAndPoint();
    }

    public void initPaintAndPoint(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        //初始化数据点
        // 初始化数据点

        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        // 初始化控制点

        mCtrl[0]  = mData[0]+mDifference;
        mCtrl[1]  = mData[1];

        mCtrl[2]  = mData[2];
        mCtrl[3]  = mData[3]+mDifference;

        mCtrl[4]  = mData[2];
        mCtrl[5]  = mData[3]-mDifference;

        mCtrl[6]  = mData[4]+mDifference;
        mCtrl[7]  = mData[5];

        mCtrl[8]  = mData[4]-mDifference;
        mCtrl[9]  = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7]-mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7]+mDifference;

        mCtrl[14] = mData[0]-mDifference;
        mCtrl[15] = mData[1];


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w/2;
        mCenterY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateSystem(canvas);       // 绘制坐标系

        canvas.translate(mCenterX, mCenterY); // 将坐标系移动到画布中央
        canvas.scale(1,-1);                 // 翻转Y轴

        drawAuxiliaryLine(canvas);


        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(mData[0],mData[1]);

        path.cubicTo(mCtrl[0],  mCtrl[1],  mCtrl[2],  mCtrl[3],     mData[2], mData[3]);
        path.cubicTo(mCtrl[4],  mCtrl[5],  mCtrl[6],  mCtrl[7],     mData[4], mData[5]);
        path.cubicTo(mCtrl[8],  mCtrl[9],  mCtrl[10], mCtrl[11],    mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15],    mData[0], mData[1]);

        canvas.drawPath(path, mPaint);

        mCurrent += mPiece;
        if (mCurrent < mDuration){

            mData[1] -= 120/mCount;
            mCtrl[7] += 80/mCount;
            mCtrl[9] += 80/mCount;

            mCtrl[4] -= 20/mCount;
            mCtrl[10] += 20/mCount;

            postInvalidateDelayed((long) mPiece);
        }
    }
    public void drawAuxiliaryLine(Canvas canvas){

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        for (int i=0; i<8; i+=2){
            canvas.drawPoint(mData[i],mData[i+1],mPaint);
        }

        for (int i=0; i<16; i+=2){
            canvas.drawPoint(mCtrl[i], mCtrl[i+1], mPaint);
        }

        // 绘制辅助线
        mPaint.setStrokeWidth(4);

        for (int i=2, j=2; i<8; i+=2, j+=4){
            canvas.drawLine(mData[i],mData[i+1],mCtrl[j],mCtrl[j+1],mPaint);
            canvas.drawLine(mData[i],mData[i+1],mCtrl[j+2],mCtrl[j+3],mPaint);
        }
        canvas.drawLine(mData[0],mData[1],mCtrl[0],mCtrl[1],mPaint);
        canvas.drawLine(mData[0],mData[1],mCtrl[14],mCtrl[15],mPaint);
    }

    public void drawCoordinateSystem(Canvas canvas){
        canvas.save();

        canvas.translate(mCenterX, mCenterY);
        canvas.scale(1,-1);

        Paint fuzhuPaint = new Paint();
        fuzhuPaint.setColor(Color.RED);
        fuzhuPaint.setStrokeWidth(5);
        fuzhuPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -2000, 0, 2000, fuzhuPaint);
        canvas.drawLine(-2000, 0, 2000, 0, fuzhuPaint);

        canvas.restore();

    }
}
