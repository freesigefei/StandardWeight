package com.test.standardweight;

import com.test.standardweight.RandomGenerator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * ѩ������, �ƶ�, �Ƴ���Ļ����������λ��.
 */
public class SnowFlake {
    // ѩ���ĽǶ�
    private static final float ANGE_RANGE = 0.1f; // �Ƕȷ�Χ
    private static final float HALF_ANGLE_RANGE = ANGE_RANGE / 2f; // һ��ĽǶ�
    private static final float HALF_PI = (float) Math.PI / 2f; // ��PI
    private static final float ANGLE_SEED = 25f; // �Ƕ��������
    private static final float ANGLE_DIVISOR = 10000f;
    // ѩ�����ƶ��ٶ�
    private static final float INCREMENT_LOWER = 2f;
    private static final float INCREMENT_UPPER = 4f;

    // ѩ���Ĵ�С
    private static final float FLAKE_SIZE_LOWER = 7f;
    private static final float FLAKE_SIZE_UPPER = 20f;

    private final RandomGenerator mRandom; // ���������
    private final Point mPosition; // ѩ��λ��
    private float mAngle; // �Ƕ�
    private final float mIncrement; // ѩ�����ٶ�
    private final float mFlakeSize; // ѩ���Ĵ�С
    private final Paint mPaint; // ����

    private SnowFlake(RandomGenerator random, Point position, float angle, float increment, float flakeSize, Paint paint) {
        mRandom = random;
        mPosition = position;
        mIncrement = increment;
        mFlakeSize = flakeSize;
        mPaint = paint;
        mAngle = angle;
    }

    public static SnowFlake create(int width, int height, Paint paint) {
        RandomGenerator random = new RandomGenerator();
        int x = random.getRandom(width);
        int y = random.getRandom(height);
        Point position = new Point(x, y);
        float angle = random.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
        float increment = random.getRandom(INCREMENT_LOWER, INCREMENT_UPPER);
        float flakeSize = random.getRandom(FLAKE_SIZE_LOWER, FLAKE_SIZE_UPPER);
        return new SnowFlake(random, position, angle, increment, flakeSize, paint);
    }

    // ����ѩ��
    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        move(width, height);
        canvas.drawCircle(mPosition.x, mPosition.y, mFlakeSize, mPaint);
    }

    // �ƶ�ѩ��
    private void move(int width, int height) {
    	//xˮƽ������ô��Ҫ�ζ�����Ҫ�������ֵ�Ϳ��ԣ�����ȡ���ζ���
    	//��� mPosition.x�����Ϻ����Ǹ�ֵ���Ͳ���ζ���
    	double x = mPosition.x + (mIncrement * Math.cos(mAngle));
        //y����ֱ���򣬾�������
        double y = mPosition.y + (mIncrement * Math.sin(mAngle));
 
        mAngle += mRandom.getRandom(-ANGLE_SEED, ANGLE_SEED) / ANGLE_DIVISOR;
        //���������ѩ��λ�ã�����ںܶ�ʱ����ˢ��һ�Σ������������Ķ���Ч��
        mPosition.set((int) x, (int) y);

        // �Ƴ���Ļ, ���¿�ʼ
        if (!isInside(width, height)) {
            // ����ѩ��
            reset(width);
        }
    }
    
    // �ж��Ƿ�������
    private boolean isInside(int width, int height) {
        int x = mPosition.x;
        int y = mPosition.y;
        //�ж���ߣ����±ߣ��Ҳ��Ե�ϵͳ��4.4�ģ���ֻ�ǲ���5.0����ϵͳ�������һ��С����
        return x > mFlakeSize -5 && x + mFlakeSize <= width && y >= -mFlakeSize - 1 && y - mFlakeSize < height;
    }

    // ����ѩ��
    private void reset(int width) {
        mPosition.x = mRandom.getRandom(width);
        mPosition.y = (int) (-mFlakeSize - 1); // ������
        mAngle = mRandom.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
    }
}
