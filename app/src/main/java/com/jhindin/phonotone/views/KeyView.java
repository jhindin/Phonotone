package com.jhindin.phonotone.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class KeyView extends View {
    Paint mPaint;
    int mWidth, mHeight;

    public KeyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(0xffffffff);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0f, 0f, mWidth - 1, mHeight - 1, mPaint);
        canvas.drawLine(mWidth - 1, 0f, 0f, mHeight - 1, mPaint);

    }
}
