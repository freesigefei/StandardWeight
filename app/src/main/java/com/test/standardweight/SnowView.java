package com.test.standardweight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * ѩ����ͼ, DELAYʱ���ػ�, ����NUM_SNOWFLAKES��ѩ��
 */
public class SnowView extends View {

    private static final int NUM_SNOWFLAKES = 50; // ѩ������
    private static final int DELAY = 5; // �ӳ�
    private SnowFlake[] mSnowFlakes; // ѩ��

    public SnowView(Context context) {
        super(context);
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override 
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            initSnow(w, h);
        }
    }

    private void initSnow(int width, int height) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // �����
        paint.setColor(Color.WHITE); // ��ɫѩ��
        paint.setStyle(Paint.Style.FILL); // ���;
        mSnowFlakes = new SnowFlake[NUM_SNOWFLAKES];
        //mSnowFlakes���е�ѩ�������ɷŵ�������
        for (int i = 0; i < NUM_SNOWFLAKES; ++i) {
            mSnowFlakes[i] = SnowFlake.create(width, height, paint);
        }
    }

    @Override 
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //for����SnowFlake
        for (SnowFlake s : mSnowFlakes) {
        	//Ȼ����л���
            s.draw(canvas);
        }
        // ��һ��ʱ���ػ�һ��, ����Ч��
        getHandler().postDelayed(runnable, DELAY);
    }

    // �ػ��߳�
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
        	//�Զ�ˢ��
            invalidate();
        }
    };
}
