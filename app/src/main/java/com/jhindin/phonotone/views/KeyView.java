package com.jhindin.phonotone.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class KeyView extends View {
    Paint mPaint;

    public KeyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0f, 0f, 100f, 100f, mPaint);
        canvas.drawLine(100f, 0f, 0f, 100f, mPaint);

    }
}
